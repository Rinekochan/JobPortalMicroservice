package com.hoang.jobapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JobApplicationId {
    private String jobId;
    private String candidateId;
}
