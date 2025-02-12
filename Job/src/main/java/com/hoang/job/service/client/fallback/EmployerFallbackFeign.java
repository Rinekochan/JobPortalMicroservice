package com.hoang.job.service.client.fallback;

import com.hoang.job.dto.EmployerDto;
import com.hoang.job.service.client.EmployerFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class EmployerFallbackFeign implements EmployerFeignClient {

    @Override
    public ResponseEntity<EmployerDto> getEmployer(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }
}
