package com.hoang.jobapplication.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @Valid
    private CandidateDto candidate;

    @Valid
    private JobDto job;

    private LocalDateTime applicationDate;

    @NotEmpty(message = "Status can not be a null or empty")
    @Size(max = 20, message = "The length of the status should not be bigger than 20 chars")
    private String status;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
