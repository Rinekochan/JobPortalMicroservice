package com.hoang.employer.controller;

import com.hoang.employer.dto.CompanyDto;
import com.hoang.employer.dto.ResponseDto;
import com.hoang.employer.entity.Company;
import com.hoang.employer.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
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

    @GetMapping
    public ResponseEntity<CompanyDto> getCompany(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getCompany(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CompanyDto>> getAllCompanys() {
        return ResponseEntity.status(HttpStatus.OK).body(companyService.getAllCompanies());
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCompany(@RequestBody CompanyDto companyDto) {
        Company company = companyService.createCompany(companyDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder()
                        .statusCode("201")
                        .statusMsg("Company created successfully with id " + company.getId())
                        .build()
                );
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCompany(@RequestBody CompanyDto companyDto) {
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

    @DeleteMapping("/delete")
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
}
