package com.hoang.jobapplication.service.client.fallback;

import com.hoang.jobapplication.dto.CandidateDto;
import com.hoang.jobapplication.service.client.CandidateFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class CandidateFallbackFeign implements CandidateFeignClient {

    @Override
    public ResponseEntity<CandidateDto> getCandidate(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }
}
