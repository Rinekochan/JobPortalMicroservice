package com.hoang.jobapplication.service;

import com.hoang.jobapplication.dto.JobApplicationEagerDto;
import com.hoang.jobapplication.dto.JobApplicationLazyDto;
import com.hoang.jobapplication.entity.JobApplication;

import java.util.List;


public interface JobApplicationService {

    /**
     * Gets job by id.
     *
     * @param jobId       the job id
     * @param candidateId the candidate id
     * @return the job by id
     */
    JobApplicationEagerDto getJobApplication(String jobId, String candidateId);

    /**
     * Gets all jobs.
     *
     * @return the all jobs
     */
    List<JobApplicationLazyDto> getAllJobApplications();

    /**
     * Gets all jobs by posted by.
     *
     * @param candidateId the candidate id
     * @return the all jobs by posted by
     */
    List<JobApplicationLazyDto> getAllJobApplicationsByCandidateId(String candidateId);

    /**
     * Gets all jobs by company id.
     *
     * @param jobId the job id
     * @return the all jobs by company id
     */
    List<JobApplicationLazyDto> getAllJobApplicationsByJobId(String jobId);

    /**
     * Create job.
     *
     * @param jobApplicationLazyDto the job application lazy dto
     * @return the job
     */
    JobApplication createJobApplication(JobApplicationLazyDto jobApplicationLazyDto);

    /**
     * Update job.
     *
     * @param jobApplicationLazyDto the job application lazy dto
     * @return the boolean
     */
    boolean updateJobApplication(JobApplicationLazyDto jobApplicationLazyDto);

    /**
     * Delete job by id.
     *
     * @param jobId       the job id
     * @param candidateId the candidate id
     * @return the boolean
     */
    boolean deleteJobApplication(String jobId, String candidateId);

    /**
     * Delete all job applications by job id.
     *
     * @param jobId the job id
     * @return the boolean
     */
    boolean deleteAllJobApplicationsByJobId(String jobId);

    /**
     * Delete all job applications by candidate id.
     *
     * @param candidateId the candidate id
     * @return the boolean
     */
    boolean deleteAllJobApplicationsByCandidateId(String candidateId);

}
