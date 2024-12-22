package com.hoang.job.entity;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Setter @Getter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "employer")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String company;

    private String industry;

    private String location;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "postedBy", fetch = FetchType.LAZY)
    private List<Job> jobs;
}
