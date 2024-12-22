package com.hoang.candidate.service;

import com.hoang.candidate.constant.Constants;
import com.hoang.candidate.dto.CandidateDto;
import com.hoang.candidate.entity.Candidate;
import com.hoang.candidate.exception.ResourceNotFoundException;
import com.hoang.candidate.mapper.CandidateMapper;
import com.hoang.candidate.repository.CandidateRepository;
import com.hoang.candidate.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    private final UserFeignClient userFeignClient;

    @Override
    public CandidateDto getCandidate(String id) {

        CandidateDto candidateDto = CandidateMapper.mapToCandidateDto(candidateRepository.getCandidateById(id).orElseThrow(
                () -> new ResourceNotFoundException(Constants.CANDIDATE_TAG, "ID", id)
        ));

        candidateDto.setUser(userFeignClient.getUser(id).getBody());

        return candidateDto;
    }

    @Override
    public List<CandidateDto> getAllCandidates() {
        return candidateRepository.findAll()
                .stream()
                .map(candidate -> {
                    CandidateDto candidateDto = CandidateMapper.mapToCandidateDto(candidate);
                    candidateDto.setUser(userFeignClient.getUser(candidateDto.getId()).getBody());
                    return candidateDto;
                })
                .toList();
    }

    @Override
    public Candidate createCandidate(CandidateDto candidateDto) {
        Candidate candidate = candidateRepository.save(CandidateMapper.mapToCandidate(candidateDto));

        candidateDto.getUser().setId(candidate.getId());

        userFeignClient.createUser(candidateDto.getUser());

        return candidate;
    }

    @Override
    public boolean updateCandidate(CandidateDto candidateDto) {
        boolean isUpdated = false;

        String candidateId = candidateDto.getId();

        Candidate candidate = candidateRepository.getCandidateById(candidateId).orElseThrow(
                () -> new ResourceNotFoundException(Constants.CANDIDATE_TAG, "ID", candidateId)
        );
        Candidate candidateFromDto = CandidateMapper.mapToCandidate(candidateDto);

        if(candidate.equals(candidateFromDto)) { // If there's nothing different, don't call update
            return !isUpdated;
        }

        candidateRepository.save(candidateFromDto);

        if(candidateDto.getUser() != null) {
            userFeignClient.updateUser(candidateDto.getUser());
        }

        return !isUpdated;
    }

    @Override
    public boolean deleteCandidate(String id) {
        boolean isDeleted = false;

        Candidate candidate = candidateRepository.getCandidateById(id).orElseThrow(
                () -> new ResourceNotFoundException(Constants.CANDIDATE_TAG, "ID", id)
        );

        userFeignClient.deleteUser(candidate.getId());

        candidateRepository.deleteCandidateById(candidate.getId());

        return !isDeleted;
    }
}
