package com.hoang.jobapplication.service;

import com.hoang.jobapplication.constant.Constants;
import com.hoang.jobapplication.dto.*;
import com.hoang.jobapplication.entity.JobApplication;
import com.hoang.jobapplication.entity.JobApplicationId;
import com.hoang.jobapplication.exception.DuplicateResourceException;
import com.hoang.jobapplication.exception.ResourceNotFoundException;
import com.hoang.jobapplication.helper.NotificationHelper;
import com.hoang.jobapplication.mapper.JobApplicationMapper;
import com.hoang.jobapplication.repository.JobApplicationRepository;
import com.hoang.jobapplication.service.client.CandidateFeignClient;
import com.hoang.jobapplication.service.client.JobFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    private final CandidateFeignClient candidateFeignClient;

    private final JobFeignClient jobFeignClient;

    private final StreamBridge streamBridge;

    @Override
    public JobApplicationEagerDto getJobApplication(String jobId, String candidateId) {
        JobApplication jobApplication =
                jobApplicationRepository.getJobApplicationById(new JobApplicationId(jobId, candidateId))
                        .orElseThrow(() -> new ResourceNotFoundException(Constants.JOB_APPLICATION_TAG, "ID", jobId + " " + candidateId)
                        );

        JobApplicationEagerDto jobApplicationEagerDto = JobApplicationMapper.mapToJobApplicationEagerDto(jobApplication);
        jobApplicationEagerDto.setJob(jobFeignClient.getJob(jobId).getBody());
        jobApplicationEagerDto.setCandidate(candidateFeignClient.getCandidate(candidateId).getBody());

        return jobApplicationEagerDto;
    }

    @Override
    public List<JobApplicationLazyDto> getAllJobApplications() {
        return jobApplicationRepository.findAll()
                .stream()
                .map(JobApplicationMapper::mapToJobApplicationLazyDto)
                .toList();
    }

    @Override
    public List<JobApplicationLazyDto> getAllJobApplicationsByCandidateId(String candidateId) {
        return jobApplicationRepository.findAll()
                .stream()
                .filter(it -> it.getId().getCandidateId().equals(candidateId))
                .map(JobApplicationMapper::mapToJobApplicationLazyDto)
                .toList();
    }

    @Override
    public List<JobApplicationLazyDto> getAllJobApplicationsByJobId(String jobId) {
        return jobApplicationRepository.findAll()
                .stream()
                .filter(it -> it.getId().getJobId().equals(jobId))
                .map(JobApplicationMapper::mapToJobApplicationLazyDto)
                .toList();
    }

    @Override
    public JobApplication createJobApplication(JobApplicationLazyDto jobApplicationLazyDto) {

        String jobId = jobApplicationLazyDto.getJobId();
        String candidateId = jobApplicationLazyDto.getCandidateId();

        if((jobApplicationRepository.getJobApplicationById(new JobApplicationId(jobId, candidateId))).isPresent()) {
            throw new DuplicateResourceException("Resource with the id of " + jobId
                    + " and " + candidateId + " already exists");
        }

        // Send notification email when the candidate successfully submit job application
        sendNotification(jobId, candidateId, true);
        return jobApplicationRepository.save(JobApplicationMapper.mapToJobApplicationLazy(jobApplicationLazyDto));
    }

    @Override
    public boolean updateJobApplication(JobApplicationLazyDto jobApplicationLazyDto) {

        boolean isUpdated = false;
        String jobId = jobApplicationLazyDto.getJobId();
        String candidateId = jobApplicationLazyDto.getCandidateId();

        JobApplication jobApplication = JobApplicationMapper.mapToJobApplicationEager(getJobApplication
                (jobId, candidateId));
        jobApplication.setId(new JobApplicationId(jobId, candidateId));

        JobApplication jobApplicationFromDto = JobApplicationMapper.mapToJobApplicationLazy(jobApplicationLazyDto);

        if (jobApplication.equals(jobApplicationFromDto)) { // If there's nothing different, don't call update
            return !isUpdated;
        }

        // Send notification email when the employer successfully update the job application status
        sendNotification(jobId, candidateId, false);
        jobApplicationRepository.save(jobApplicationFromDto);

        return !isUpdated;
    }

    private void sendNotification(String jobId, String candidateId, boolean isCreated) {
        CandidateDto candidate = candidateFeignClient.getCandidate(candidateId).getBody();
        JobDto job = jobFeignClient.getJob(jobId).getBody();
        assert candidate != null;
        assert job != null;

        EmailRequestDto email = isCreated
                ? NotificationHelper.buildJobCreateEmail(candidate, job)
                : NotificationHelper.buildJobUpdateEmail(candidate, job);

        streamBridge.send("sendCommunication-out-0", email);
    }


    @Override
    public boolean deleteJobApplication(String jobId, String candidateId) {

        boolean isDeleted = false;

        JobApplication jobApplication = JobApplicationMapper.mapToJobApplicationEager
                (getJobApplication(jobId, candidateId));

        jobApplicationRepository.deleteJobApplicationById(jobApplication.getId());

        return !isDeleted;
    }

    @Override
    public boolean deleteAllJobApplicationsByJobId(String jobId) {

        boolean isDeleted = false;

        jobApplicationRepository.deleteJobApplicationsById_JobId(jobId);

        return !isDeleted;
    }

    @Override
    public boolean deleteAllJobApplicationsByCandidateId(String candidateId) {

        boolean isDeleted = false;

        jobApplicationRepository.deleteJobApplicationsById_CandidateId(candidateId);

        return !isDeleted;
    }

}
