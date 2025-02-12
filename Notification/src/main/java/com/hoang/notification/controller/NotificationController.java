package com.hoang.notification.controller;

import com.hoang.notification.dto.EmailRequestDto;
import com.hoang.notification.dto.ResponseDto;
import com.hoang.notification.service.NotificationService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notification CRUD REST APIs")
@AllArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private NotificationService notificationService;

    @Operation(summary = "POST NOTIFICATION")
    @PostMapping("/send")
    @Retry(name = "sendNotification", fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> sendNotification(@RequestBody EmailRequestDto email) {
        notificationService.sendNotificationEmail(email);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseDto.builder()
                        .statusCode("200")
                        .statusMsg("Send email request notification successfully")
                        .build());
    }

    /**
     * Service unavailable fallback response entity.
     *
     * @param ex the exception
     * @return the response entity
     */
    public ResponseEntity<ResponseDto> serviceUnavailableFallback(Exception ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                ResponseDto.builder()
                        .statusCode("503")
                        .statusMsg(ex.getMessage() + " with the error message: " + ex.getCause().getMessage())
                        .build());
    }
}
