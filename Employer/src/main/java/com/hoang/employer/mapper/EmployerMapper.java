package com.hoang.employer.mapper;

import com.hoang.employer.dto.EmployerDto;
import com.hoang.employer.entity.Employer;

public class EmployerMapper {

    private EmployerMapper() {}

    public static Employer mapToEmployer(EmployerDto employerDto) {
        return Employer.builder()
                .id(employerDto.getId())
                .company(CompanyMapper.mapToCompany(employerDto.getCompany()))
                .build();
    }

    public static EmployerDto mapToEmployerDto(Employer employer) {
        return EmployerDto.builder()
                .id(employer.getId())
                .company(CompanyMapper.mapToCompanyDto(employer.getCompany()))
                .build();
    }
}
