package com.hoang.notification.service.client;

import com.hoang.notification.dto.EmailDetailDto;
import com.hoang.notification.dto.EmailResponseDto;
import com.hoang.notification.service.client.fallback.BrevoFallbackFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "email-client", url = "https://api.brevo.com", fallback = BrevoFallbackFeign.class)
public interface BrevoFeignClient {

    @PostMapping(value = "/v3/smtp/email", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    EmailResponseDto sendEmail(@RequestHeader("api-key") String apiKey, @RequestBody EmailDetailDto emailDetailDto);
}
