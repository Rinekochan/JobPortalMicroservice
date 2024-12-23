package com.hoang.employer.repository;

import com.hoang.employer.entity.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, String> {
    Optional<Employer> getEmployerById(String id);

    void deleteEmployerById(String id);
}
