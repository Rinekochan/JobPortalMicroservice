package com.hoang.employer.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;

@Data @Builder
public class EmployerEagerDto implements Serializable {

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
