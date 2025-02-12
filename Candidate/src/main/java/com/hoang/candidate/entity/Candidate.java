package com.hoang.candidate.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "candidate_db")
public class Candidate {

    @MongoId
    private String id;

    private String skills;

    private String experience;

    private String description;

}
