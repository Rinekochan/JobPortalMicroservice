package com.hoang.employer.service;

import com.hoang.employer.constant.Constants;
import com.hoang.employer.dto.EmployerEagerDto;
import com.hoang.employer.dto.EmployerLazyDto;
import com.hoang.employer.entity.Employer;
import com.hoang.employer.exception.FeignConnectionFailure;
import com.hoang.employer.exception.ResourceNotFoundException;
import com.hoang.employer.mapper.EmployerMapper;
import com.hoang.employer.repository.EmployerRepository;
import com.hoang.employer.service.client.UserFeignClient;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;

    private final UserFeignClient userFeignClient;

    @Override
    public EmployerEagerDto getEmployer(String id) {

        EmployerEagerDto employerEagerDto = EmployerMapper.mapToEmployerDto(employerRepository.getEmployerById((id)).orElseThrow(
                () -> new ResourceNotFoundException(Constants.EMPLOYER_TAG, "ID", id)
        ));

        employerEagerDto.setUser(userFeignClient.getUser(id).getBody());

        return employerEagerDto;
    }

    @Override
    public List<EmployerLazyDto> getAllEmployers() {
        return employerRepository.findAll()
                .stream()
                .map(employer -> {
                    EmployerEagerDto employerEagerDto = EmployerMapper.mapToEmployerDto(employer);
                    employerEagerDto.setUser(userFeignClient.getUser(employer.getId()).getBody());

                    return EmployerLazyDto.builder()
                            .id(employerEagerDto.getId())
                            .user(employerEagerDto.getUser())
                            .companyId(employerEagerDto.getCompany().getId())
                            .build();
                })
                .toList();
    }

    @Override
    public Employer createEmployer(EmployerEagerDto employerEagerDto) {
        Employer employer = employerRepository.save(EmployerMapper.mapToEmployer(employerEagerDto));

        employerEagerDto.getUser().setId(employer.getId());

        HttpStatusCode statusCode = userFeignClient.createUser(employerEagerDto.getUser()).getStatusCode();

        if(statusCode == HttpStatus.SERVICE_UNAVAILABLE) { // If we can't create User, don't create Employer
            throw new FeignConnectionFailure("There's a problem connecting with User, aborting operation");
        }

        return employer;
    }

    @Override
    public boolean updateEmployer(EmployerEagerDto employerEagerDto) {
        boolean isUpdated = false;

        String employerId = employerEagerDto.getId();

        Employer employer = EmployerMapper.mapToEmployer(getEmployer(employerId));

        Employer employerFromDto = EmployerMapper.mapToEmployer(employerEagerDto);

        if(employer.equals(employerFromDto)) { // If there's nothing different, don't call update
            return !isUpdated;
        }

        employerRepository.save(employerFromDto);

        if(employerEagerDto.getUser() != null) {
            userFeignClient.updateUser(employerEagerDto.getUser());
        }

        return !isUpdated;
    }

    @Override
    public boolean deleteEmployer(String id) {
        boolean isDeleted = false;

        Employer employer = EmployerMapper.mapToEmployer(getEmployer(id));

        HttpStatusCode statusCode = userFeignClient.getUser(id).getStatusCode();

        if(statusCode == HttpStatus.SERVICE_UNAVAILABLE) { // If we can't delete User, don't delete Employer
            throw new FeignConnectionFailure("There's a problem connecting with User, aborting operation");
        }

        userFeignClient.deleteUser(id);

        employerRepository.deleteEmployerById(employer.getId());

        return !isDeleted;
    }
}
