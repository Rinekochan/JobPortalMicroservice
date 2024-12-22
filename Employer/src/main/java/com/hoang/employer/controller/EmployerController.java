package com.hoang.employer.controller;

import com.hoang.employer.dto.EmployerLazyDto;
import com.hoang.employer.dto.EmployerEagerDto;
import com.hoang.employer.dto.ResponseDto;
import com.hoang.employer.entity.Employer;
import com.hoang.employer.exception.ResourceNotFoundException;
import com.hoang.employer.service.CompanyService;
import com.hoang.employer.service.EmployerService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Employer CRUD REST APIs")
@RestController
@AllArgsConstructor
@RequestMapping("/api/employer")
public class EmployerController {

    private final EmployerService employerService;

    private final CompanyService companyService;

    /**
     * Gets employer.
     *
     * @param id the id
     * @return the employer
     */
    @Operation(summary = "GET EMPLOYER BY ID")
    @GetMapping
    @Retry(name = "getEmployer", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<EmployerEagerDto> getEmployer(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(employerService.getEmployer(id));
    }

    /**
     * Gets all employers.
     *
     * @return the all employers
     */
    @Operation(summary = "GET ALL EMPLOYERS")
    @GetMapping("/all")
    @Retry(name = "getAllEmployers", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<List<EmployerLazyDto>> getAllEmployers() {
        return ResponseEntity.status(HttpStatus.OK).body(employerService.getAllEmployers());
    }

    /**
     * Create employer response entity.
     *
     * @param employerLazyDto the employer lazy dto
     * @return the response entity
     */
    @Operation(summary = "CREATE EMPLOYER")
    @PostMapping("/create")
    @Retry(name = "createEmployer", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> createEmployer(@RequestBody EmployerLazyDto employerLazyDto) {

        EmployerEagerDto employerValid;

        try {
            employerValid = EmployerEagerDto.builder()
                    .user(employerLazyDto.getUser())
                    .company(companyService.getCompany(employerLazyDto.getCompanyId()))
                    .build();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Company", "Id", employerLazyDto.getCompanyId());
        }

        Employer employer = employerService.createEmployer(employerValid);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder()
                        .statusCode("201")
                        .statusMsg("Employer created successfully with id " + employer.getId())
                        .build()
                );
    }

    /**
     * Update employer response entity.
     *
     * @param employerLazyDto the employer lazy dto
     * @return the response entity
     */
    @Operation(summary = "UPDATE EMPLOYER")
    @Retry(name = "updateEmployer", fallbackMethod = "serviceUnavailableFallback")
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateEmployer(@RequestBody EmployerLazyDto employerLazyDto) {

        EmployerEagerDto employerValid;
        try {
            employerValid = EmployerEagerDto.builder()
                    .id(employerLazyDto.getId())
                    .user(employerLazyDto.getUser())
                    .company(companyService.getCompany(employerLazyDto.getCompanyId()))
                    .build();
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Company", "Id", employerLazyDto.getCompanyId());
        }

        boolean isUpdated = employerService.updateEmployer(employerValid);
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
     * Delete employer response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "DELETE EMPLOYER")
    @Retry(name = "deleteEmployer", fallbackMethod = "serviceUnavailableFallback")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteEmployer(@RequestParam String id) {
        boolean isDeleted = employerService.deleteEmployer(id);
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
