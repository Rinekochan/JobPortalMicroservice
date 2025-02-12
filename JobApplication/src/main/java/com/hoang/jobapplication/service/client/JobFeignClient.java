package com.hoang.jobapplication.service.client;

import com.hoang.jobapplication.dto.JobDto;
import com.hoang.jobapplication.service.client.fallback.CandidateFallbackFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "job", fallback = CandidateFallbackFeign.class)
public interface JobFeignClient {

    @GetMapping(value = "api/job", consumes = "application/json")
    ResponseEntity<JobDto> getJob(@RequestParam String id);
}
