package com.hoang.employer.service.client.fallback;

import com.hoang.employer.constant.Constants;
import com.hoang.employer.dto.ResponseDto;
import com.hoang.employer.service.client.JobFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class JobFallbackFeign implements JobFeignClient {

    @Override
    public ResponseEntity<ResponseDto> deleteJobByPostedBy(@RequestParam String id) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ResponseDto.builder()
                        .statusCode("503")
                        .statusMsg(Constants.FEIGN_CLIENT_ERROR)
                        .build()
                );
    }
}
