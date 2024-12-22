package com.hoang.employer.controller;

import com.hoang.employer.dto.EmployerRequestDto;
import com.hoang.employer.dto.EmployerDto;
import com.hoang.employer.dto.ResponseDto;
import com.hoang.employer.entity.Employer;
import com.hoang.employer.exception.ResourceNotFoundException;
import com.hoang.employer.service.CompanyService;
import com.hoang.employer.service.EmployerService;
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

    @GetMapping
    public ResponseEntity<EmployerDto> getEmployer(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(employerService.getEmployer(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployerDto>> getAllEmployers() {
        return ResponseEntity.status(HttpStatus.OK).body(employerService.getAllEmployers());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEmployer(@RequestBody EmployerRequestDto employerRequestDto) {

        EmployerDto employerValid;

        try {
            employerValid = EmployerDto.builder()
                    .user(employerRequestDto.getUser())
                    .company(companyService.getCompany(employerRequestDto.getCompanyId()))
                    .build();
        } catch(ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Company", "Id", employerRequestDto.getCompanyId());
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

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateEmployer(@RequestBody EmployerRequestDto employerRequestDto) {

        EmployerDto employerValid;
        try {
            employerValid = EmployerDto.builder()
                    .id(employerRequestDto.getId())
                    .user(employerRequestDto.getUser())
                    .company(companyService.getCompany(employerRequestDto.getCompanyId()))
                    .build();
        } catch(ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Company", "Id", employerRequestDto.getCompanyId());
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
}
