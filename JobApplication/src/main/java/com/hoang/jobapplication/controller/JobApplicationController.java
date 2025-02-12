package com.hoang.jobapplication.controller;

import com.hoang.jobapplication.constant.Constants;
import com.hoang.jobapplication.dto.JobApplicationEagerDto;
import com.hoang.jobapplication.dto.JobApplicationLazyDto;
import com.hoang.jobapplication.dto.ResponseDto;
import com.hoang.jobapplication.entity.JobApplication;
import com.hoang.jobapplication.service.JobApplicationService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "JobApplication CRUD REST APIs")
@RestController
@AllArgsConstructor
@RequestMapping("/api/application")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    /**
     * Gets job application.
     *
     * @param jobId       the job id
     * @param candidateId the candidate id
     * @return the job application
     */
    @Operation(summary = "GET JOB APPLICATION BY JOB ID AND CANDIDATE ID")
    @GetMapping
    @Retry(name = "getJobApplication", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<JobApplicationEagerDto> getJobApplication(@RequestParam String jobId, @RequestParam String candidateId) {
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.getJobApplication(jobId, candidateId));
    }

    /**
     * Gets all job applications.
     *
     * @return the all job applications
     */
    @Operation(summary = "GET ALL JOBS")
    @GetMapping("/all")
    @Retry(name = "getAllJobApplications", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<List<JobApplicationLazyDto>> getAllJobApplications() {
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.getAllJobApplications());
    }

    /**
     * Gets all job applications by job id.
     *
     * @param id the id
     * @return the all job applications by job id
     */
    @Operation(summary = "GET ALL JOBS BY JOB ID")
    @GetMapping("/filter/job")
    @Retry(name = "getAllJobApplicationsByJobId", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<List<JobApplicationLazyDto>> getAllJobApplicationsByJobId(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.getAllJobApplicationsByJobId(id));
    }

    /**
     * Gets job applications by candidate id.
     *
     * @param id the id
     * @return the job applications by candidate id
     */
    @Operation(summary = "GET ALL JOBS BY CANDIDATE ID")
    @GetMapping("/filter/candidate")
    @Retry(name = "getJobApplicationsByCandidateId", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<List<JobApplicationLazyDto>> getJobApplicationsByCandidateId(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.getAllJobApplicationsByCandidateId(id));
    }

    /**
     * Create job application response entity.
     *
     * @param jobApplicationLazyDto the job application lazy dto
     * @return the response entity
     */
    @Operation(summary = "CREATE JOB APPLICATION")
    @PostMapping("/create")
    @Retry(name = "createJobApplication", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> createJobApplication(@Valid @RequestBody JobApplicationLazyDto jobApplicationLazyDto) {
        JobApplication jobApplication = jobApplicationService.createJobApplication(jobApplicationLazyDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder()
                        .statusCode("201")
                        .statusMsg("JobApplication created successfully with id " + jobApplication.getId())
                        .build()
                );
    }

    /**
     * Update job application response entity.
     *
     * @param jobApplicationLazyDto the job application lazy dto
     * @return the response entity
     */
    @Operation(summary = "UPDATE JOB APPLICATION")
    @PutMapping("/update")
    @Retry(name = "updateJobApplication", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> updateJobApplication(@Valid @RequestBody JobApplicationLazyDto jobApplicationLazyDto) {
        boolean isUpdated = jobApplicationService.updateJobApplication(jobApplicationLazyDto);
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
     * Delete job application response entity.
     *
     * @param jobId       the job id
     * @param candidateId the candidate id
     * @return the response entity
     */
    @Operation(summary = "DELETE JOB APPLICATION BY ID")
    @DeleteMapping("/delete")
    @Retry(name = "deleteJobApplication", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> deleteJobApplication(@RequestParam String jobId, @RequestParam String candidateId) {
        boolean isDeleted = jobApplicationService.deleteJobApplication(jobId, candidateId);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode("200")
                            .statusMsg(Constants.DELETE_SUCCESSFUL_TAG)
                            .build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder()
                            .statusCode("417")
                            .statusMsg(Constants.DELETE_FAILED_TAG)
                            .build());
        }
    }

    /**
     * Delete job application by job id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "DELETE ALL JOB APPLICATIONS BY JOB ID")
    @DeleteMapping("/delete/job")
    @Retry(name = "deleteJobApplication", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> deleteJobApplicationByJobId(@RequestParam String id) {
        boolean isDeleted = jobApplicationService.deleteAllJobApplicationsByJobId(id);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode("200")
                            .statusMsg(Constants.DELETE_SUCCESSFUL_TAG)
                            .build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder()
                            .statusCode("417")
                            .statusMsg(Constants.DELETE_FAILED_TAG)
                            .build());
        }
    }

    /**
     * Delete job application by candidate id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "DELETE JOB APPLICATIONS BY CANDIDATE ID")
    @DeleteMapping("/delete/candidate")
    @Retry(name = "deleteJobApplication", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> deleteJobApplicationByCandidateId(@RequestParam String id) {
        boolean isDeleted = jobApplicationService.deleteAllJobApplicationsByCandidateId(id);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode("200")
                            .statusMsg(Constants.DELETE_SUCCESSFUL_TAG)
                            .build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder()
                            .statusCode("417")
                            .statusMsg(Constants.DELETE_FAILED_TAG)
                            .build());
        }
    }

    /**
     * Service unavailable fallback response entity.
     *
     * @param ex the exception
     * @return the response entity
     */
    public ResponseEntity<ResponseDto> serviceUnavailableFallback(Exception ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                ResponseDto.builder()
                        .statusCode("503")
                        .statusMsg(ex.getMessage() + " with the error message: " + ex.getCause().getMessage())
                        .build());
    }
}
