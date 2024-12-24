package com.hoang.job.service;

import com.hoang.job.constant.Constants;
import com.hoang.job.dto.JobEagerDto;
import com.hoang.job.dto.JobLazyDto;
import com.hoang.job.entity.Job;
import com.hoang.job.exception.ResourceNotFoundException;
import com.hoang.job.mapper.JobMapper;
import com.hoang.job.repository.JobRepository;
import com.hoang.job.service.client.EmployerFeignClient;
import com.hoang.job.service.client.JobApplicationFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    private final EmployerFeignClient employerFeignClient;

    private final JobApplicationFeignClient jobApplicationFeignClient;

    @Override
    public JobEagerDto getJob(String id) {

        Job job = jobRepository.getJobById(id).orElseThrow(
                () -> new ResourceNotFoundException(Constants.JOB_TAG, "ID", id)
        );

        JobEagerDto jobEagerDto = JobMapper.mapToJobEagerDto(job);

        jobEagerDto.setPostedBy(employerFeignClient.getEmployer(job.getPostedBy()).getBody());

        return jobEagerDto;
    }

    @Override
    public List<JobLazyDto> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(JobMapper::mapToJobLazyDto)
                .toList();
    }

    @Override
    public List<JobLazyDto> getAllJobsByPostedBy(String postedBy) {
        return jobRepository.getJobsByPostedBy(postedBy)
                .stream()
                .map(JobMapper::mapToJobLazyDto)
                .toList();
    }

    @Override
    public List<JobLazyDto> getAllJobsByCompanyId(String companyId) {
        return jobRepository.findAll()
                .stream()
                .filter(it -> Objects.requireNonNull(employerFeignClient.getEmployer(it.getPostedBy())
                        .getBody()).getCompany().getId().equals(companyId))
                .map(JobMapper::mapToJobLazyDto)
                .toList();
    }

    @Override
    public Job createJob(JobLazyDto jobLazyDto) {
        return jobRepository.save(JobMapper.mapToJobLazy(jobLazyDto));
    }

    @Override
    public boolean updateJob(JobLazyDto jobLazyDto) {
        boolean isUpdated = false;

        Job job = JobMapper.mapToJobEager(getJob(jobLazyDto.getId()));
        job.setPostedBy(jobLazyDto.getPostedBy());

        Job jobFromDto = JobMapper.mapToJobLazy(jobLazyDto);

        if(job.equals(jobFromDto)) { // If there's nothing different, don't call update
            return !isUpdated;
        }

        jobRepository.save(jobFromDto);

        return !isUpdated;
    }

    @Override
    public boolean deleteJob(String id) {
        boolean isDeleted = false;

        Job job = JobMapper.mapToJobEager(getJob(id));

        jobRepository.deleteJobById(job.getId());

        jobApplicationFeignClient.deleteJobApplicationByJobId(job.getId()); // If a job is deleted, all of its applications are deleted too

        return !isDeleted;
    }

    @Override
    public boolean deleteJobByPostedBy(String postedBy) {
        boolean isDeleted = false;

        List<JobLazyDto> jobsByPostedBy = getAllJobsByPostedBy(postedBy);

        jobRepository.deleteJobByPostedBy(postedBy);

        for (JobLazyDto job : jobsByPostedBy) {
            jobApplicationFeignClient.deleteJobApplicationByJobId(job.getId()); // If a job is deleted, all of its applications are deleted too
        }
        return !isDeleted;
    }
}
