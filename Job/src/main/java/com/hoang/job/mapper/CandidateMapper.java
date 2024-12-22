package com.hoang.job.mapper;

import com.hoang.job_portal_project.dto.CandidateDto;
import com.hoang.job_portal_project.entity.Candidate;

public class CandidateMapper {

    private CandidateMapper() {}

    public static Candidate mapToCandidate(CandidateDto candidateDto) {
        return Candidate.builder()
                .user(UserMapper.mapToUser(candidateDto.getUser()))
                .skills(candidateDto.getSkills())
                .experience(candidateDto.getExperience())
                .description(candidateDto.getDescription())
                .build();
    }

    public static CandidateDto mapToCandidateDto(Candidate candidate) {
        return CandidateDto.builder()
                .user(UserMapper.mapToUserDto(candidate.getUser()))
                .skills(candidate.getSkills())
                .experience(candidate.getExperience())
                .description(candidate.getDescription())
                .build();
    }
}
