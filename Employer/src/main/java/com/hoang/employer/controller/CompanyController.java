package com.hoang.employer.controller;

import com.hoang.employer.dto.CompanyDto;
import com.hoang.employer.dto.ResponseDto;
import com.hoang.employer.entity.Company;
import com.hoang.employer.service.CompanyService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Company CRUD REST APIs")
@RestController
@AllArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    /**
     * Gets company.
     *
     * @param id the id
     * @return the company
     */
    @Operation(summary = "GET COMPANY BY ID")
    @GetMapping
    @Retry(name = "getCompany", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<CompanyDto> getCompany(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompany(id));
    }

    /**
     * Gets all companies.
     *
     * @return the all companies
     */
    @Operation(summary = "GET ALL COMPANIES")
    @GetMapping("/all")
    @Retry(name = "getAllCompanies", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompanies());
    }

    /**
     * Create company response entity.
     *
     * @param companyDto the company dto
     * @return the response entity
     */
    @Operation(summary = "CREATE COMPANY")
    @PostMapping("/create")
    @Retry(name = "createCompany", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> createCompany(@Valid @RequestBody CompanyDto companyDto) {
        Company company = companyService.createCompany(companyDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder()
                        .statusCode("201")
                        .statusMsg("Company created successfully with id " + company.getId())
                        .build()
                );
    }

    /**
     * Update company response entity.
     *
     * @param companyDto the company dto
     * @return the response entity
     */
    @Operation(summary = "UPDATE COMPANY")
    @PutMapping("/update")
    @Retry(name = "updateCompany", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> updateCompany(@Valid @RequestBody CompanyDto companyDto) {
        boolean isUpdated = companyService.updateCompany(companyDto);
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
     * Delete company response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "DELETE COMPANY")
    @DeleteMapping("/delete")
    @Retry(name = "deleteCompany", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> deleteCompany(@RequestParam String id) {
        boolean isDeleted = companyService.deleteCompany(id);
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
