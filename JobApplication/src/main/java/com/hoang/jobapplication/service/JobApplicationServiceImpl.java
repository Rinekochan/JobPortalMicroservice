package com.hoang.jobapplication.service;

import com.hoang.jobapplication.constant.Constants;
import com.hoang.jobapplication.dto.JobApplicationEagerDto;
import com.hoang.jobapplication.dto.JobApplicationLazyDto;
import com.hoang.jobapplication.entity.JobApplication;
import com.hoang.jobapplication.entity.JobApplicationId;
import com.hoang.jobapplication.exception.DuplicateResourceException;
import com.hoang.jobapplication.exception.ResourceNotFoundException;
import com.hoang.jobapplication.mapper.JobApplicationMapper;
import com.hoang.jobapplication.repository.JobApplicationRepository;
import com.hoang.jobapplication.service.client.CandidateFeignClient;
import com.hoang.jobapplication.service.client.JobFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    private final CandidateFeignClient candidateFeignClient;

    private final JobFeignClient jobFeignClient;

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
        String jobId = jobApplicationLazyDto.getId().getJobId();
        String candidateId = jobApplicationLazyDto.getId().getCandidateId();

        if(getJobApplication(jobId, candidateId) != null) {
            throw new DuplicateResourceException("Resource with the id of " + jobId
                    + " and " + candidateId + " already exists");
        }

        return jobApplicationRepository.save(JobApplicationMapper.mapToJobApplicationLazy(jobApplicationLazyDto));
    }

    @Override
    public boolean updateJobApplication(JobApplicationLazyDto jobApplicationLazyDto) {
        boolean isUpdated = false;

        JobApplication jobApplication = JobApplicationMapper.mapToJobApplicationEager(getJobApplication
                (jobApplicationLazyDto.getId().getJobId(), jobApplicationLazyDto.getId().getCandidateId()));
        jobApplication.setId(jobApplicationLazyDto.getId());

        JobApplication jobApplicationFromDto = JobApplicationMapper.mapToJobApplicationLazy(jobApplicationLazyDto);

        if (jobApplication.equals(jobApplicationFromDto)) { // If there's nothing different, don't call update
            return !isUpdated;
        }

        jobApplicationRepository.save(jobApplicationFromDto);

        return !isUpdated;
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
