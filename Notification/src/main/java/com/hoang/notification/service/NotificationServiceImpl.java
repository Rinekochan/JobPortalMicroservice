package com.hoang.notification.service;

import com.hoang.notification.dto.EmailDetailDto;
import com.hoang.notification.dto.EmailRequestDto;
import com.hoang.notification.dto.SenderDto;
import com.hoang.notification.exception.FeignConnectionFailure;
import com.hoang.notification.service.client.BrevoFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final BrevoFeignClient mailService;

    @Value("${spring.mail.username}")
    private String senderEmail;

    @Value("${spring.mail.name}")
    private String senderName;

    @Value("${spring.mail.key}")
    private String key;

    @Autowired
    public NotificationServiceImpl(BrevoFeignClient mailService) {
        this.mailService = mailService;
    }

    // This method is used for production-notification
    @Override
    @Async
    public void sendNotificationEmail(EmailRequestDto emailRequest) {
        try {
            EmailDetailDto email = EmailDetailDto.builder()
                    .sender(
                            SenderDto.builder()
                                    .name(senderName)
                                    .email(senderEmail)
                                    .build()
                    )
                    .to(emailRequest.getTo())
                    .subject(emailRequest.getSubject())
                    .htmlContent(emailRequest.getHtmlContent())
                    .build();

            mailService.sendEmail(key, email);
        } catch (Exception e) {
            throw new FeignConnectionFailure("There's problem connecting to the Brevo server, please try again later.");
        }
    }
}
