package com.hoang.employer.service;

import com.hoang.employer.constant.Constants;
import com.hoang.employer.dto.EmployerDto;
import com.hoang.employer.entity.Employer;
import com.hoang.employer.exception.ResourceNotFoundException;
import com.hoang.employer.mapper.EmployerMapper;
import com.hoang.employer.repository.EmployerRepository;
import com.hoang.employer.service.client.UserFeignClient;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class EmployerServiceImpl implements EmployerService {

    private final EmployerRepository employerRepository;

    private final UserFeignClient userFeignClient;

    @Override
    public EmployerDto getEmployer(String id) {

        EmployerDto employerDto = EmployerMapper.mapToEmployerDto(employerRepository.getEmployerById((id)).orElseThrow(
                () -> new ResourceNotFoundException(Constants.EMPLOYER_TAG, "ID", id)
        ));

        employerDto.setUser(userFeignClient.getUser(id).getBody());

        return employerDto;
    }

    @Override
    public List<EmployerDto> getAllEmployers() {
        return employerRepository.findAll()
                .stream()
                .map(employer -> {
                    EmployerDto employerDto = EmployerMapper.mapToEmployerDto(employer);
                    employerDto.setUser(userFeignClient.getUser(employerDto.getId()).getBody());
                    return employerDto;
                })
                .toList();
    }

    @Override
    public Employer createEmployer(EmployerDto employerDto) {
        Employer employer = employerRepository.save(EmployerMapper.mapToEmployer(employerDto));

        employerDto.getUser().setId(employer.getId());

        userFeignClient.createUser(employerDto.getUser());

        return employer;
    }

    @Override
    public boolean updateEmployer(EmployerDto employerDto) {
        boolean isUpdated = false;

        String employerId = employerDto.getId();

        Employer employer = employerRepository.getEmployerById(employerId).orElseThrow(
                () -> new ResourceNotFoundException(Constants.EMPLOYER_TAG, "ID", employerId)
        );
        Employer employerFromDto = EmployerMapper.mapToEmployer(employerDto);

        if(employer.equals(employerFromDto)) { // If there's nothing different, don't call update
            return !isUpdated;
        }

        employerRepository.save(EmployerMapper.mapToEmployer(employerDto));

        if(employerDto.getUser() != null) {
            userFeignClient.updateUser(employerDto.getUser());
        }

        return !isUpdated;
    }

    @Override
    public boolean deleteEmployer(String id) {
        boolean isDeleted = false;

        Employer employer = employerRepository.getEmployerById(id).orElseThrow(
                () -> new ResourceNotFoundException(Constants.EMPLOYER_TAG, "ID", id)
        );

        userFeignClient.deleteUser(id);

        employerRepository.deleteEmployerById(employer.getId());

        return !isDeleted;
    }
}
