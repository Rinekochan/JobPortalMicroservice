package com.hoang.candidate.service.client;

import com.hoang.candidate.dto.ResponseDto;
import com.hoang.candidate.service.client.fallback.JobApplicationFallbackFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "job-application", fallback = JobApplicationFallbackFeign.class)
public interface JobApplicationFeignClient {

    @DeleteMapping(value = "api/application/delete/candidate", consumes = "application/json")
    ResponseEntity<ResponseDto> deleteJobApplicationByCandidateId(@RequestParam String id);
}
