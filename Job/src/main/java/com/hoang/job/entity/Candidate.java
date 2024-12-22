package com.hoang.job.entity;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "candidate")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String skills;

    private String experience;

    private String description;

    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @OneToMany(mappedBy = "candidate")
    private List<Application> applications;
}
