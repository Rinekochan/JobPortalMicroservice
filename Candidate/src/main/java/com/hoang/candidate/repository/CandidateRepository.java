package com.hoang.candidate.repository;

import com.hoang.candidate.entity.Candidate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepository extends MongoRepository<Candidate, String> {

    Optional<Candidate> getCandidateById(String id);

    void deleteCandidateById(String id);
}
