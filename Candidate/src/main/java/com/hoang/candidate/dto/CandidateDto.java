package com.hoang.candidate.dto;

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

    private String skills;

    private String experience;

    private String description;

    private UserDto user;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
