package com.hoang.candidate.controller;

import com.hoang.candidate.dto.CandidateDto;
import com.hoang.candidate.dto.ResponseDto;
import com.hoang.candidate.entity.Candidate;
import com.hoang.candidate.service.CandidateService;
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

    @GetMapping
    public ResponseEntity<CandidateDto> getCandidate(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(candidateService.getCandidate(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CandidateDto>> getAllCandidates() {
        return ResponseEntity.status(HttpStatus.OK).body(candidateService.getAllCandidates());
    }

    @PostMapping("/create")
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

    @PutMapping("/update")
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

    @DeleteMapping("/delete")
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
}
