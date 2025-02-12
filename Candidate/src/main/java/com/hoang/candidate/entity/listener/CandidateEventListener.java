package com.hoang.candidate.entity.listener;

import com.hoang.candidate.entity.Candidate;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
public class CandidateEventListener extends AbstractMongoEventListener<Candidate> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Candidate> event) {

        super.onBeforeConvert(event);
        Candidate entity = event.getSource();

        // Just in case
    }
}