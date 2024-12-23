package com.hoang.candidate.service.client.fallback;

import com.hoang.candidate.constant.Constants;
import com.hoang.candidate.dto.ResponseDto;
import com.hoang.candidate.service.client.JobApplicationFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class JobApplicationFallbackFeign implements JobApplicationFeignClient {

    @Override
    public ResponseEntity<ResponseDto> deleteJobApplicationByCandidateId(@RequestParam String id) {
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ResponseDto.builder()
                        .statusCode("503")
                        .statusMsg(Constants.FEIGN_CLIENT_ERROR)
                        .build()
                );
    }
}
