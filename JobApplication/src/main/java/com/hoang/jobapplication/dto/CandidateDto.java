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

@Data @Builder
public class CandidateDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    @Size(max = 255, message = "The length of the skills should not be bigger than 255 chars")
    private String skills;

    @Size(max = 255, message = "The length of the experience should not be bigger than 255 chars")
    private String experience;

    @Size(max = 255, message = "The length of the description should not be bigger than 255 chars")
    private String description;

    @Valid
    private UserDto user;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
