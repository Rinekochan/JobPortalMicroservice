package com.hoang.notification.service;

import com.hoang.notification.dto.EmailDetailDto;

/**
 * The interface Email service.
 */
public interface EmailService {

    /**
     * Send hello world email.
     *
     * @param email the email
     */
    void sendHelloWorldEmail(EmailDetailDto email);

    /**
     * Send notification email.
     *
     * @param email the email
     */
    void sendNotificationEmail(EmailDetailDto email);
}
