package com.hoang.jobapplication.entity.listener;

import com.hoang.jobapplication.entity.JobApplication;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class JobApplicationEventListener extends AbstractMongoEventListener<JobApplication> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<JobApplication> event) {

        super.onBeforeConvert(event);
        JobApplication entity = event.getSource();

        if (entity.getApplicationDate() == null) {
            entity.setApplicationDate(LocalDateTime.now());
        }
    }
}