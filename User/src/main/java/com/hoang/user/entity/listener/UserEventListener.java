package com.hoang.user.entity.listener;

import com.hoang.user.entity.User;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class UserEventListener extends AbstractMongoEventListener<User> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<User> event) {

        super.onBeforeConvert(event);
        User entity = event.getSource();

        if (entity.getCreatedAt() == null) {
            entity.setCreatedAt(LocalDateTime.now());
        }

        if (!Objects.equals(entity.getUpdatedAt(), LocalDateTime.now())) {
            entity.setUpdatedAt(LocalDateTime.now());
        }
    }
}