package com.hoang.jobapplication.mapper;


import com.hoang.jobapplication.dto.JobApplicationEagerDto;
import com.hoang.jobapplication.dto.JobApplicationLazyDto;
import com.hoang.jobapplication.entity.JobApplication;

public class JobApplicationMapper {

    private JobApplicationMapper() {}

    public static JobApplication mapToJobApplicationLazy(JobApplicationLazyDto jobApplicationLazyDto) {
        return JobApplication.builder()
                .id(jobApplicationLazyDto.getId())
                .applicationDate(jobApplicationLazyDto.getApplicationDate())
                .status(jobApplicationLazyDto.getStatus())
                .build();
    }

    public static JobApplicationLazyDto mapToJobApplicationLazyDto(JobApplication jobApplication) {
        return JobApplicationLazyDto.builder()
                .id(jobApplication.getId())
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
