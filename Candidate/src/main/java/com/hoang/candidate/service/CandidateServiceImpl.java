package com.hoang.candidate.service;

import com.hoang.candidate.constant.Constants;
import com.hoang.candidate.dto.CandidateDto;
import com.hoang.candidate.entity.Candidate;
import com.hoang.candidate.exception.FeignConnectionFailure;
import com.hoang.candidate.exception.ResourceNotFoundException;
import com.hoang.candidate.mapper.CandidateMapper;
import com.hoang.candidate.repository.CandidateRepository;
import com.hoang.candidate.service.client.JobApplicationFeignClient;
import com.hoang.candidate.service.client.UserFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    private final UserFeignClient userFeignClient;

    private final JobApplicationFeignClient jobApplicationFeignClient;

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

        HttpStatusCode statusCode = userFeignClient.createUser(candidateDto.getUser()).getStatusCode();

        if(statusCode == HttpStatus.SERVICE_UNAVAILABLE) { // If we can't create User, don't create Candidate
            candidateRepository.deleteCandidateById(candidate.getId());
            throw new FeignConnectionFailure("There's a problem connecting with User, aborting operation");
        }

        userFeignClient.createUser(candidateDto.getUser());

        return candidate;
    }

    @Override
    public boolean updateCandidate(CandidateDto candidateDto) {
        boolean isUpdated = false;

        String candidateId = candidateDto.getId();

        Candidate candidate = CandidateMapper.mapToCandidate(getCandidate(candidateId));

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

        Candidate candidate = CandidateMapper.mapToCandidate(getCandidate(id));

        HttpStatusCode statusCode = userFeignClient.getUser(id).getStatusCode();

        if(statusCode == HttpStatus.SERVICE_UNAVAILABLE) { // If we can't delete User, don't delete Candidate
            throw new FeignConnectionFailure("There's a problem connecting with User, aborting operation");
        }

        userFeignClient.deleteUser(candidate.getId()); // If a candidate is deleted, delete its associated user

        candidateRepository.deleteCandidateById(candidate.getId());

        jobApplicationFeignClient.deleteJobApplicationByCandidateId(id); // If a candidate is deleted, delete all of its application

        return !isDeleted;
    }
}
