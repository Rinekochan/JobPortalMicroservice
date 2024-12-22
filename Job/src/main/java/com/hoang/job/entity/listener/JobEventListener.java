package com.hoang.job.entity.listener;

import com.hoang.job.entity.Job;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class JobEventListener extends AbstractMongoEventListener<Job> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Job> event) {

        super.onBeforeConvert(event);
        Job entity = event.getSource();

        if (entity.getPostedDate() == null) {
            entity.setPostedDate(LocalDateTime.now());
        }
    }
}