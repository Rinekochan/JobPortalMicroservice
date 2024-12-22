package com.hoang.candidate.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.UUID;

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
