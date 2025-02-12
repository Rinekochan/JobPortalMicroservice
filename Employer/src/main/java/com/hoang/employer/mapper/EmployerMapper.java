package com.hoang.employer.mapper;

import com.hoang.employer.dto.EmployerEagerDto;
import com.hoang.employer.entity.Employer;

public class EmployerMapper {

    private EmployerMapper() {}

    public static Employer mapToEmployer(EmployerEagerDto employerEagerDto) {
        return Employer.builder()
                .id(employerEagerDto.getId())
                .company(CompanyMapper.mapToCompany(employerEagerDto.getCompany()))
                .build();
    }

    public static EmployerEagerDto mapToEmployerDto(Employer employer) {
        return EmployerEagerDto.builder()
                .id(employer.getId())
                .company(CompanyMapper.mapToCompanyDto(employer.getCompany()))
                .build();
    }
}
