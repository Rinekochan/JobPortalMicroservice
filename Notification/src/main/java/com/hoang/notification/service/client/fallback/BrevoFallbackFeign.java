package com.hoang.notification.service.client.fallback;

import com.hoang.notification.dto.EmailDetailDto;
import com.hoang.notification.dto.EmailResponseDto;
import com.hoang.notification.service.client.BrevoFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
public class BrevoFallbackFeign implements BrevoFeignClient {

    public EmailResponseDto sendEmail(@RequestHeader("api-key") String apiKey, @RequestBody EmailDetailDto emailDetailDto) {
        return null;
    }
}
