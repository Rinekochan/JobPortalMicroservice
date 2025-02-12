package com.hoang.job.repository;

import com.hoang.job.entity.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {

    Optional<Job> getJobById(String id);

    List<Job> getJobsByPostedBy(String postedBy);

    void deleteJobById(String id);

    void deleteJobByPostedBy(String postedBy);
}
