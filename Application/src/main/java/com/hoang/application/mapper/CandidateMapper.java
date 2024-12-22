package com.hoang.application.mapper;

import com.hoang.application.dto.CandidateDto;
import com.hoang.application.entity.Candidate;

public class CandidateMapper {

    private CandidateMapper() {}

    public static Candidate mapToCandidate(CandidateDto candidateDto) {
        return Candidate.builder()
                .skills(candidateDto.getSkills())
                .experience(candidateDto.getExperience())
                .description(candidateDto.getDescription())
                .build();
    }

    public static CandidateDto mapToCandidateDto(Candidate candidate) {
        return CandidateDto.builder()
                .skills(candidate.getSkills())
                .experience(candidate.getExperience())
                .description(candidate.getDescription())
                .build();
    }
}
