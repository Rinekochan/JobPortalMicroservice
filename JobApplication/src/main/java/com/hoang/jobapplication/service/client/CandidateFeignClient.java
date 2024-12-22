package com.hoang.jobapplication.service.client;

import com.hoang.jobapplication.dto.CandidateDto;
import com.hoang.jobapplication.service.client.fallback.CandidateFallbackFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "candidate", fallback = CandidateFallbackFeign.class)
public interface CandidateFeignClient {

    @GetMapping(value = "api/candidate", consumes = "application/json")
    ResponseEntity<CandidateDto> getCandidate(@RequestParam String id);
}

