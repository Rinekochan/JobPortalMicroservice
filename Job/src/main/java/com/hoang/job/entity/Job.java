package com.hoang.job.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;

    private String description;

    private String requirement;

    private String salary;

    private String location;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime postedDate;

    @ManyToOne
    @JoinColumn(name = "posted_by", updatable = false)
    private Employer postedBy;

    private LocalDateTime expiryDate;

    @OneToMany(mappedBy = "job")
    private List<Application> applications;
}
