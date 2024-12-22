package com.hoang.employer.service;

import com.hoang.employer.constant.Constants;
import com.hoang.employer.dto.CompanyDto;
import com.hoang.employer.entity.Company;
import com.hoang.employer.exception.ResourceNotFoundException;
import com.hoang.employer.mapper.CompanyMapper;
import com.hoang.employer.repository.CompanyRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    
    private final CompanyRepository companyRepository;

    @Override
    public CompanyDto getCompany(String id) {
        Company company = companyRepository.getCompanyById((id)).orElseThrow(
                () -> new ResourceNotFoundException(Constants.COMPANY_TAG, "id", id));

        return CompanyMapper.mapToCompanyDto(company);
    }

    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(CompanyMapper::mapToCompanyDto)
                .toList();
    }

    @Override
    public Company createCompany(CompanyDto companyDto) {
        return companyRepository.save(CompanyMapper.mapToCompany(companyDto));
    }

    @Override
    public boolean updateCompany(CompanyDto companyDto) {
        boolean isUpdated = false;

        Company company = companyRepository.getCompanyById(companyDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException(Constants.COMPANY_TAG, "ID", companyDto.getId())
        );
        Company companyFromDto = CompanyMapper.mapToCompany(companyDto);

        if(company.equals(companyFromDto)) { // If there's nothing different, don't call update
            return !isUpdated;
        }

        companyRepository.save(CompanyMapper.mapToCompany(companyDto));

        return !isUpdated;
    }

    @Override
    public boolean deleteCompany(String id) {
        boolean isDeleted = false;

        Company company = companyRepository.getCompanyById(id).orElseThrow(
                () -> new ResourceNotFoundException(Constants.COMPANY_TAG, "ID", id)
        );

        companyRepository.deleteCompanyById(company.getId());

        return !isDeleted;
    }
}
