package com.hoang.job.service;

import com.hoang.job.dto.JobEagerDto;
import com.hoang.job.dto.JobLazyDto;
import com.hoang.job.entity.Job;

import java.util.List;

/**
 * The interface Job service.
 */
public interface JobService {

    /**
     * Gets job by id.
     *
     * @param id the id
     * @return the job by id
     */
    JobEagerDto getJob(String id);

    /**
     * Gets all jobs.
     *
     * @return the all jobs
     */
    List<JobLazyDto> getAllJobs();

    /**
     * Gets all jobs by posted by.
     *
     * @param postedBy the posted by
     * @return the all jobs by posted by
     */
    List<JobLazyDto> getAllJobsByPostedBy(String postedBy);

    /**
     * Gets all jobs by company id.
     *
     * @param companyId the company id
     * @return the all jobs by company id
     */
    List<JobLazyDto> getAllJobsByCompanyId(String companyId);

    /**
     * Create job.
     *
     * @param jobLazyDto the job dto
     * @return the job
     */
    Job createJob(JobLazyDto jobLazyDto);

    /**
     * Update job.
     *
     * @param jobLazyDto the job dto
     * @return the boolean
     */
    boolean updateJob(JobLazyDto jobLazyDto);

    /**
     * Delete job by id.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteJob(String id);
}
