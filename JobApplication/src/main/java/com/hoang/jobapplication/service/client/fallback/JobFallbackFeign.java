package com.hoang.jobapplication.service.client.fallback;

import com.hoang.jobapplication.dto.JobDto;
import com.hoang.jobapplication.service.client.JobFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class JobFallbackFeign implements JobFeignClient {

    @Override
    public ResponseEntity<JobDto> getJob(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }
}
