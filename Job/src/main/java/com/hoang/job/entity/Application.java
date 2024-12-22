package com.hoang.job.entity;

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
@Table(name = "application")
public class Application {

    @EmbeddedId
    private ApplicationKey id;

    @ManyToOne
    @MapsId("candidateId")
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @ManyToOne
    @MapsId("jobId")
    @JoinColumn(name = "job_id")
    private Job job;

    private LocalDateTime applicationDate;

    private String status;
}
