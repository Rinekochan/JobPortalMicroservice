package com.hoang.employer.repository;

import com.hoang.employer.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    Optional<Company> getCompanyById(String id);

    void deleteCompanyById(String id);
}
