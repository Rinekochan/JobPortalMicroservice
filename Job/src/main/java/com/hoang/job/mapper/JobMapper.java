package com.hoang.job.mapper;

import com.hoang.job_portal_project.dto.JobDto;
import com.hoang.job_portal_project.entity.Job;

public class JobMapper {

    private JobMapper() {}

    public static Job mapToJob(JobDto jobDto) {
        return Job.builder()
                .id(jobDto.getId())
                .title(jobDto.getTitle())
                .description(jobDto.getDescription())
                .requirement(jobDto.getRequirement())
                .salary(jobDto.getSalary())
                .location(jobDto.getLocation())
                .postedDate(jobDto.getPostedDate())
                .postedBy(EmployerMapper.mapToEmployer(jobDto.getPostedBy()))
                .expiryDate(jobDto.getExpiryDate())
                .build();
    }

    public static JobDto mapToJobDto(Job job) {
        return JobDto.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .requirement(job.getRequirement())
                .salary(job.getSalary())
                .location(job.getLocation())
                .postedDate(job.getPostedDate())
                .postedBy(EmployerMapper.mapToEmployerDto(job.getPostedBy()))
                .expiryDate(job.getExpiryDate())
                .build();
    }
}
