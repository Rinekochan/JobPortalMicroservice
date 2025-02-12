package com.hoang.job.mapper;


import com.hoang.job.dto.JobEagerDto;
import com.hoang.job.dto.JobLazyDto;
import com.hoang.job.entity.Job;

public class JobMapper {

    private JobMapper() {}

    public static Job mapToJobLazy(JobLazyDto jobLazyDto) {
        return Job.builder()
                .id(jobLazyDto.getId())
                .title(jobLazyDto.getTitle())
                .description(jobLazyDto.getDescription())
                .requirement(jobLazyDto.getRequirement())
                .salary(jobLazyDto.getSalary())
                .location(jobLazyDto.getLocation())
                .postedDate(jobLazyDto.getPostedDate())
                .postedBy(jobLazyDto.getPostedBy())
                .expiryDate(jobLazyDto.getExpiryDate())
                .build();
    }

    public static JobLazyDto mapToJobLazyDto(Job job) {
        return JobLazyDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirement(job.getRequirement())
                .salary(job.getSalary())
                .location(job.getLocation())
                .postedDate(job.getPostedDate())
                .postedBy(job.getPostedBy())
                .expiryDate(job.getExpiryDate())
                .build();
    }

    public static Job mapToJobEager(JobEagerDto jobEagerDto) {
        return Job.builder()
                .id(jobEagerDto.getId())
                .title(jobEagerDto.getTitle())
                .description(jobEagerDto.getDescription())
                .requirement(jobEagerDto.getRequirement())
                .salary(jobEagerDto.getSalary())
                .location(jobEagerDto.getLocation())
                .postedDate(jobEagerDto.getPostedDate())
                .expiryDate(jobEagerDto.getExpiryDate())
                .build();
    }

    public static JobEagerDto mapToJobEagerDto(Job job) {
        return JobEagerDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirement(job.getRequirement())
                .salary(job.getSalary())
                .location(job.getLocation())
                .postedDate(job.getPostedDate())
                .expiryDate(job.getExpiryDate())
                .build();
    }
}
