package com.hoang.job.entity;

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
public class Job {

    @MongoId
    private String id;

    private String title;

    private String description;

    private String requirement;

    private String salary;

    private String location;

    @CreatedDate
    private LocalDateTime postedDate;

    private String postedBy;

    private LocalDateTime expiryDate;
}
