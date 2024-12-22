package com.hoang.employer.entity;

import com.hoang.employer.entity.generator.GeneratedId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employer")
public class Employer {

    @Id
    @GeneratedId
    private String id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
