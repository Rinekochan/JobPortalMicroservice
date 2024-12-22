package com.hoang.jobapplication.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class JobApplicationEagerDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private CandidateDto candidate;

    private JobDto job;

    private LocalDateTime applicationDate;

    private String status;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
