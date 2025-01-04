package com.hoang.jobapplication.helper;

import com.hoang.jobapplication.constant.Constants;
import com.hoang.jobapplication.dto.CandidateDto;
import com.hoang.jobapplication.dto.EmailRequestDto;
import com.hoang.jobapplication.dto.JobDto;
import com.hoang.jobapplication.dto.RecipientDto;

import java.util.List;

public class NotificationHelper {

    public static EmailRequestDto buildJobCreateEmail(CandidateDto candidate, JobDto job) {
        return EmailRequestDto.builder()
                .to(List.of(
                        RecipientDto.builder()
                                .email(candidate.getUser().getEmail())
                                .name(candidate.getUser().getName())
                                .build()
                ))
                .htmlContent(
                        "<h3> Hi, " + candidate.getUser().getName() + "</h3>"
                                + Constants.JOB_APPLICATION_CREATION
                                + String.format("<a href='localhost:3000/api/application?jobId=%s&candidateId=%s'>Your application</a>", job.getId(), candidate.getId()))
                .subject("Job Application Success")
                .build();
    }

    public static EmailRequestDto buildJobUpdateEmail(CandidateDto candidate, JobDto job) {
        return EmailRequestDto.builder()
                .to(List.of(
                        RecipientDto.builder()
                                .email(candidate.getUser().getEmail())
                                .name(candidate.getUser().getName())
                                .build()
                ))
                .htmlContent(
                        "<h3> Hi, " + candidate.getUser().getName() + "</h3>"
                                + Constants.JOB_APPLICATION_UPDATE
                                + String.format("<a href='localhost:3000/api/application?jobId=%s&candidateId=%s'>Your application</a>", job.getId(), candidate.getId()))
                .subject("Job Application Update")
                .build();
    }

}
