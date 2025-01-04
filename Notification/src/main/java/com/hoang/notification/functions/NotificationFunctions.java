package com.hoang.notification.functions;

import com.hoang.notification.dto.EmailRequestDto;
import com.hoang.notification.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class NotificationFunctions {

    @Bean
    public Function<EmailRequestDto, Boolean> email(NotificationService notificationService){
        return email -> {
            try {
                notificationService.sendNotificationEmail(email);
                return true;
            } catch (Exception e) {
                return false;
            }
        };

    }
}
