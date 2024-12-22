package com.hoang.job.service.client;

import com.hoang.job.dto.EmployerDto;
import com.hoang.job.service.client.fallback.EmployerFallbackFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "employer", fallback = EmployerFallbackFeign.class)
public interface EmployerFeignClient {

    @GetMapping(value = "api/employer", consumes = "application/json")
    ResponseEntity<EmployerDto> getEmployer(@RequestParam String id);
}

