package com.hoang.job.service.client;

import com.hoang.job.dto.ResponseDto;
import com.hoang.job.service.client.fallback.JobApplicationFallbackFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "job-application", fallback = JobApplicationFallbackFeign.class)
public interface JobApplicationFeignClient {

    @DeleteMapping(value = "api/application/delete/job", consumes = "application/json")
    ResponseEntity<ResponseDto> deleteJobApplicationByJobId(@RequestParam String id);
}
