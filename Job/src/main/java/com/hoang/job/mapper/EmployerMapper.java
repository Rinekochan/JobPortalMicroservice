package com.hoang.job.mapper;

import com.hoang.job_portal_project.dto.EmployerDto;
import com.hoang.job_portal_project.entity.Employer;

public class EmployerMapper {

    private EmployerMapper() {}

    public static Employer mapToEmployer(EmployerDto employerDto) {
        return Employer.builder()
                .user(UserMapper.mapToUser(employerDto.getUser()))
                .company(employerDto.getCompany())
                .industry(employerDto.getIndustry())
                .location(employerDto.getLocation())
                .build();
    }

    public static EmployerDto mapToEmployerDto(Employer employer) {
        return EmployerDto.builder()
                .user(UserMapper.mapToUserDto(employer.getUser()))
                .company(employer.getCompany())
                .industry(employer.getIndustry())
                .location(employer.getLocation())
                .build();
    }
}
