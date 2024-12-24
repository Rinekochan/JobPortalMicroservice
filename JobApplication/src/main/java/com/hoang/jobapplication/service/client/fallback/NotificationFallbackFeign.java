package com.hoang.jobapplication.service.client.fallback;

import com.hoang.jobapplication.dto.EmailRequestDto;
import com.hoang.jobapplication.dto.ResponseDto;
import com.hoang.jobapplication.service.client.NotificationFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class NotificationFallbackFeign implements NotificationFeignClient {

    @Override
    public ResponseEntity<ResponseDto> sendNotification(@RequestBody EmailRequestDto email) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }
}
