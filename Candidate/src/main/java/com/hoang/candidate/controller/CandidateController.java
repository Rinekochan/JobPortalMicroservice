package com.hoang.candidate.controller;

import com.hoang.candidate.dto.CandidateDto;
import com.hoang.candidate.dto.ResponseDto;
import com.hoang.candidate.entity.Candidate;
import com.hoang.candidate.service.CandidateService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Candidate CRUD REST APIs")
@RestController
@AllArgsConstructor
@RequestMapping("/api/candidate")
public class CandidateController {
    private final CandidateService candidateService;

    /**
     * Gets candidate.
     *
     * @param id the id
     * @return the candidate
     */
    @Operation(summary = "GET CANDIDATE BY ID")
    @GetMapping
    @Retry(name = "getCandidate", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<CandidateDto> getCandidate(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(candidateService.getCandidate(id));
    }

    /**
     * Gets all candidates.
     *
     * @return the all candidates
     */
    @Operation(summary = "GET ALL CANDIDATES")
    @GetMapping("/all")
    @Retry(name = "getAllCandidates", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<List<CandidateDto>> getAllCandidates() {
        return ResponseEntity.status(HttpStatus.OK).body(candidateService.getAllCandidates());
    }

    /**
     * Create candidate response entity.
     *
     * @param candidateDto the candidate dto
     * @return the response entity
     */
    @Operation(summary = "CREATE CANDIDATE")
    @PostMapping("/create")
    @Retry(name = "createCandidate", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> createCandidate(@RequestBody CandidateDto candidateDto) {
        Candidate candidate = candidateService.createCandidate(candidateDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder()
                        .statusCode("201")
                        .statusMsg("Candidate created successfully with id " + candidate.getId())
                        .build()
                );
    }

    /**
     * Update candidate response entity.
     *
     * @param candidateDto the candidate dto
     * @return the response entity
     */
    @Operation(summary = "UPDATE CANDIDATE")
    @PutMapping("/update")
    @Retry(name = "updateCandidate", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> updateCandidate(@RequestBody CandidateDto candidateDto) {
        boolean isUpdated = candidateService.updateCandidate(candidateDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode("200")
                            .statusMsg("Update request processed successfully")
                            .build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder()
                            .statusCode("417")
                            .statusMsg("Update operation failed")
                            .build());
        }
    }

    /**
     * Delete candidate response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "DELETE CANDIDATE")
    @DeleteMapping("/delete")
    @Retry(name = "deleteCandidate", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> deleteCandidate(@RequestParam String id) {
        boolean isDeleted = candidateService.deleteCandidate(id);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode("200")
                            .statusMsg("Delete request processed successfully")
                            .build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder()
                            .statusCode("417")
                            .statusMsg("Delete operation failed")
                            .build());
        }
    }

    /**
     * Service unavailable fallback response entity.
     *
     * @param throwable the throwable
     * @return the response entity
     */
    public ResponseEntity<ResponseDto> serviceUnavailableFallback(Throwable throwable) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }
}
