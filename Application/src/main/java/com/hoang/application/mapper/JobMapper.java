package com.hoang.application.mapper;

import com.hoang.application.dto.JobDto;
import com.hoang.application.entity.Job;

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
                .expiryDate(job.getExpiryDate())
                .build();
    }
}
