package com.hoang.notification.service;

import com.hoang.notification.dto.EmailDetailDto;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Async;

public class EmailServiceImpl implements EmailService {

    

    Logger log = Logger.getLogger(EmailServiceImpl.class);

    @Override
    @Async
    public void sendHelloWorldEmail(EmailDetailDto email) {

    }

    @Override
    @Async
    public void sendNotificationEmail(EmailDetailDto email) {

    }
}
