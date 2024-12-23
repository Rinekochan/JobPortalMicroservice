package com.hoang.job.controller;

import com.hoang.job.dto.JobEagerDto;
import com.hoang.job.dto.JobLazyDto;
import com.hoang.job.dto.ResponseDto;
import com.hoang.job.entity.Job;
import com.hoang.job.service.JobService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Job CRUD REST APIs")
@RestController
@AllArgsConstructor
@RequestMapping("/api/job")
public class JobController {
    private final JobService jobService;

    /**
     * Gets job.
     *
     * @param id the id
     * @return the job
     */
    @Operation(summary = "GET JOB BY ID")
    @GetMapping
    @Retry(name = "getJob", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<JobEagerDto> getJob(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getJob(id));
    }

    /**
     * Gets all jobs.
     *
     * @return the all jobs
     */
    @Operation(summary = "GET ALL JOBS")
    @GetMapping("/all")
    @Retry(name = "getAllJobs", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<List<JobLazyDto>> getAllJobs() {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getAllJobs());
    }

    /**
     * Gets jobs by posted by.
     *
     * @param id the id
     * @return the jobs by posted by
     */
    @Operation(summary = "GET ALL JOBS BY POSTED BY")
    @GetMapping("/filter/posted-by")
    @Retry(name = "getJobsByPostedBy", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<List<JobLazyDto>> getJobsByPostedBy(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getAllJobsByPostedBy(id));
    }

    /**
     * Gets jobs by company id.
     *
     * @param id the id
     * @return the jobs by company id
     */
    @Operation(summary = "GET ALL JOBS BY COMPANY ID")
    @GetMapping("/filter/company")
    @Retry(name = "getJobsByCompanyId", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<List<JobLazyDto>> getJobsByCompanyId(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(jobService.getAllJobsByCompanyId(id));
    }

    /**
     * Create job response entity.
     *
     * @param jobLazyDto the job lazy dto
     * @return the response entity
     */
    @Operation(summary = "CREATE JOB")
    @PostMapping("/create")
    @Retry(name = "createJob", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> createJob(@Valid @RequestBody JobLazyDto jobLazyDto) {
        Job job = jobService.createJob(jobLazyDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder()
                        .statusCode("201")
                        .statusMsg("Job created successfully with id " + job.getId())
                        .build()
                );
    }

    /**
     * Update job response entity.
     *
     * @param jobLazyDto the job lazy dto
     * @return the response entity
     */
    @Operation(summary = "UPDATE JOB")
    @PutMapping("/update")
    @Retry(name = "updateJob", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> updateJob(@Valid @RequestBody JobLazyDto jobLazyDto) {
        boolean isUpdated = jobService.updateJob(jobLazyDto);
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
     * Delete job response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "DELETE JOB")
    @DeleteMapping("/delete")
    @Retry(name = "deleteJob", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> deleteJob(@RequestParam String id) {
        boolean isDeleted = jobService.deleteJob(id);
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

    @Operation(summary = "DELETE ALL JOBS BY POSTED BY")
    @DeleteMapping("/delete/posted-by")
    @Retry(name = "deleteJobByPostedBy", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> deleteJobByPostedBy(@RequestParam String id) {
        boolean isDeleted = jobService.deleteJobByPostedBy(id);
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
