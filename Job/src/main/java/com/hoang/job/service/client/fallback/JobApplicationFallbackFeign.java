package com.hoang.job.service.client.fallback;

import com.hoang.job.constant.Constants;
import com.hoang.job.dto.ResponseDto;
import com.hoang.job.service.client.JobApplicationFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class JobApplicationFallbackFeign implements JobApplicationFeignClient {

    @Override
    public ResponseEntity<ResponseDto> deleteJobApplicationByJobId(@RequestParam String id) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ResponseDto.builder()
                        .statusCode("503")
                        .statusMsg(Constants.FEIGN_CLIENT_ERROR)
                        .build()
                );
    }
}
