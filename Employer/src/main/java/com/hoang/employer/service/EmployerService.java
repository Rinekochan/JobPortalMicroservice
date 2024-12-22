package com.hoang.employer.service;

import com.hoang.employer.dto.EmployerDto;
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
    EmployerDto getEmployer(String id);

    /**
     * Gets all employers.
     *
     * @return the all employers
     */
    List<EmployerDto> getAllEmployers();

    /**
     * Create employer.
     *
     * @param userDto the user dto
     * @return the employer
     */
    Employer createEmployer(EmployerDto userDto);

    /**
     * Update employer.
     *
     * @param userDto the user dto
     * @return the boolean
     */
    boolean updateEmployer(EmployerDto userDto);

    /**
     * Delete employer.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteEmployer(String id);

}
