package com.hoang.jobapplication.dto;

import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;

@Data @Builder
public class EmployerDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    @Valid
    private UserDto user;

    @Valid
    private CompanyDto company;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
