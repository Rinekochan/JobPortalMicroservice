package com.hoang.jobapplication.mapper;


import com.hoang.jobapplication.dto.JobApplicationEagerDto;
import com.hoang.jobapplication.dto.JobApplicationLazyDto;
import com.hoang.jobapplication.entity.JobApplication;
import com.hoang.jobapplication.entity.JobApplicationId;

public class JobApplicationMapper {

    private JobApplicationMapper() {}

    public static JobApplication mapToJobApplicationLazy(JobApplicationLazyDto jobApplicationLazyDto) {
        return JobApplication.builder()
                .id(new JobApplicationId(jobApplicationLazyDto.getJobId(), jobApplicationLazyDto.getCandidateId()))
                .applicationDate(jobApplicationLazyDto.getApplicationDate())
                .status(jobApplicationLazyDto.getStatus())
                .build();
    }

    public static JobApplicationLazyDto mapToJobApplicationLazyDto(JobApplication jobApplication) {
        return JobApplicationLazyDto.builder()
                .jobId(jobApplication.getId().getJobId())
                .candidateId(jobApplication.getId().getCandidateId())
                .applicationDate(jobApplication.getApplicationDate())
                .status(jobApplication.getStatus())
                .build();
    }

    public static JobApplication mapToJobApplicationEager(JobApplicationEagerDto jobApplicationEagerDto) {
        return JobApplication.builder()
                .applicationDate(jobApplicationEagerDto.getApplicationDate())
                .status(jobApplicationEagerDto.getStatus())
                .build();
    }

    public static JobApplicationEagerDto mapToJobApplicationEagerDto(JobApplication jobApplication) {
        return JobApplicationEagerDto.builder()
                .applicationDate(jobApplication.getApplicationDate())
                .status(jobApplication.getStatus())
                .build();
    }
}
