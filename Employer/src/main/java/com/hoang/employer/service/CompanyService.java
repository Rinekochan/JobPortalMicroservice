package com.hoang.employer.service;

import com.hoang.employer.dto.CompanyDto;
import com.hoang.employer.entity.Company;

import java.util.List;

/**
 * The interface Company service.
 */
public interface CompanyService {

    /**
     * Gets company.
     *
     * @param id the id
     * @return the company
     */
    CompanyDto getCompany(String id);

    /**
     * Gets all companies.
     *
     * @return the all companies
     */
    List<CompanyDto> getAllCompanies();

    /**
     * Create company.
     *
     * @param userDto the user dto
     * @return the company
     */
    Company createCompany(CompanyDto userDto);

    /**
     * Update company.
     *
     * @param userDto the user dto
     * @return the boolean
     */
    boolean updateCompany(CompanyDto userDto);

    /**
     * Delete company.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteCompany(String id);
}
