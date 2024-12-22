package com.hoang.application.mapper;

import com.hoang.application.dto.ApplicationDto;
import com.hoang.application.entity.Application;

public class ApplicationMapper {

    private ApplicationMapper() {}

    public static Application mapToApplication(ApplicationDto applicationDto) {
        return Application.builder()
                .job(JobMapper.mapToJob(applicationDto.getJob()))
                .candidate(CandidateMapper.mapToCandidate(applicationDto.getCandidate()))
                .applicationDate(applicationDto.getApplicationDate())
                .status(applicationDto.getStatus())
                .build();
    }

    public static ApplicationDto mapToApplicationDto(Application application) {
        return ApplicationDto.builder()
                .job(JobMapper.mapToJobDto(application.getJob()))
                .candidate(CandidateMapper.mapToCandidateDto(application.getCandidate()))
                .applicationDate(application.getApplicationDate())
                .status(application.getStatus())
                .build();
    }
}
