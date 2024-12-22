package com.hoang.application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Embeddable
@Data
class ApplicationKey implements Serializable {
    private UUID candidateId;
    private UUID jobId;
}

@Entity
@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class Application {

    @EmbeddedId
    private ApplicationKey id;

    @Column(name = "candidate_name", nullable = false)
    private String candidateName;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;


    private LocalDateTime applicationDate;

    private String status;
}
