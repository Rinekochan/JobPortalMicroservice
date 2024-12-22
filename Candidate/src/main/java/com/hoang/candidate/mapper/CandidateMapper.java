package com.hoang.candidate.mapper;


import com.hoang.candidate.dto.CandidateDto;
import com.hoang.candidate.entity.Candidate;

public class CandidateMapper {

    private CandidateMapper() {}

    public static Candidate mapToCandidate(CandidateDto candidateDto) {
        return Candidate.builder()
                .id(candidateDto.getId())
                .skills(candidateDto.getSkills())
                .experience(candidateDto.getExperience())
                .description(candidateDto.getDescription())
                .build();
    }

    public static CandidateDto mapToCandidateDto(Candidate candidate) {
        return CandidateDto.builder()
                .id(candidate.getId())
                .skills(candidate.getSkills())
                .experience(candidate.getExperience())
                .description(candidate.getDescription())
                .build();
    }
}
