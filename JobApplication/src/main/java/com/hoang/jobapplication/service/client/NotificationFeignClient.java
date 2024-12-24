package com.hoang.jobapplication.service.client;

import com.hoang.jobapplication.dto.EmailRequestDto;
import com.hoang.jobapplication.dto.ResponseDto;
import com.hoang.jobapplication.service.client.fallback.NotificationFallbackFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", fallback = NotificationFallbackFeign.class)
public interface NotificationFeignClient {

    @PostMapping(value = "api/notification/send", consumes = "application/json")
    ResponseEntity<ResponseDto> sendNotification(@RequestBody EmailRequestDto email);
}

