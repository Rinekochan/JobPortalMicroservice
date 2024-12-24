package com.hoang.notification.service;

import com.hoang.notification.dto.EmailRequestDto;

/**
 * The interface Email service.
 */
public interface NotificationService {

    /**
     * Send notification email.
     *
     * @param emailRequest the email
     */
    void sendNotificationEmail(EmailRequestDto emailRequest);
}
