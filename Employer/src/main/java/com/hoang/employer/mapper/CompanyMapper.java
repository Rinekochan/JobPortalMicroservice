package com.hoang.employer.mapper;

import com.hoang.employer.dto.CompanyDto;
import com.hoang.employer.entity.Company;

public class CompanyMapper {

    private CompanyMapper() {}

    public static Company mapToCompany(CompanyDto companyDto) {
        return Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .industry(companyDto.getIndustry())
                .location(companyDto.getLocation())
                .website(companyDto.getWebsite())
                .build();
    }

    public static CompanyDto mapToCompanyDto(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .industry(company.getIndustry())
                .location(company.getLocation())
                .website(company.getWebsite())
                .build();
    }
}
