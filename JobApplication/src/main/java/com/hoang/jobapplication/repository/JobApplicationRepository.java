package com.hoang.jobapplication.repository;

import com.hoang.jobapplication.entity.JobApplication;
import com.hoang.jobapplication.entity.JobApplicationId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobApplicationRepository extends MongoRepository<JobApplication, String> {

    Optional<JobApplication> getJobApplicationById(JobApplicationId id);

    void deleteJobApplicationById(JobApplicationId id);

    void deleteJobApplicationsById_JobId(String jobId);

    void deleteJobApplicationsById_CandidateId(String candidateId);
}
