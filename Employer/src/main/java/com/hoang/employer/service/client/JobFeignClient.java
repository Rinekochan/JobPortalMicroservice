package com.hoang.employer.service.client;

import com.hoang.employer.dto.ResponseDto;
import com.hoang.employer.service.client.fallback.JobFallbackFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "job", fallback = JobFallbackFeign.class)
public interface JobFeignClient {

    @DeleteMapping(value = "api/job/delete/posted-by", consumes = "application/json")
    ResponseEntity<ResponseDto> deleteJobByPostedBy(@RequestParam String id);
}
