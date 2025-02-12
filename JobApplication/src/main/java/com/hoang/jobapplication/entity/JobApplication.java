package com.hoang.jobapplication.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "job_db")
public class JobApplication {

    @MongoId
    private JobApplicationId id;

    @CreatedDate
    private LocalDateTime applicationDate;

    private String status;
}

