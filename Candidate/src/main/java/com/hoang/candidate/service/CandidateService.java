package com.hoang.candidate.service;

import com.hoang.candidate.dto.CandidateDto;
import com.hoang.candidate.entity.Candidate;

import java.util.List;

/**
 * The interface Candidate service.
 */
public interface CandidateService {

    /**
     * Gets candidate by id.
     *
     * @param id the id
     * @return the candidate by id
     */
    CandidateDto getCandidate(String id);

    /**
     * Gets all candidates.
     *
     * @return the all candidates
     */
    List<CandidateDto> getAllCandidates();

    /**
     * Create candidate.
     *
     * @param candidateDto the candidate dto
     * @return the candidate
     */
    Candidate createCandidate(CandidateDto candidateDto);

    /**
     * Update candidate.
     *
     * @param candidateDto the candidate dto
     * @return the boolean
     */
    boolean updateCandidate(CandidateDto candidateDto);

    /**
     * Delete candidate by id.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteCandidate(String id);
}
