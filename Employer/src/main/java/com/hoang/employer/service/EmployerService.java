package com.hoang.employer.service;

import com.hoang.employer.dto.EmployerEagerDto;
import com.hoang.employer.dto.EmployerLazyDto;
import com.hoang.employer.entity.Employer;

import java.util.List;

/**
 * The interface Employer service.
 */
public interface EmployerService {

    /**
     * Gets employer.
     *
     * @param id the id
     * @return the employer
     */
    EmployerEagerDto getEmployer(String id);

    /**
     * Gets all employers.
     *
     * @return the all employers
     */
    List<EmployerLazyDto> getAllEmployers();

    /**
     * Create employer.
     *
     * @param userDto the user dto
     * @return the employer
     */
    Employer createEmployer(EmployerEagerDto userDto);

    /**
     * Update employer.
     *
     * @param userDto the user dto
     * @return the boolean
     */
    boolean updateEmployer(EmployerEagerDto userDto);

    /**
     * Delete employer.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteEmployer(String id);

}
