package com.hoang.employer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;

@Data @Builder
public class CompanyDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    @NotEmpty(message = "Name can not be a null or empty")
    @Size(max = 50, message = "The length of the name should not be bigger than 50 chars")
    private String name;

    @NotEmpty(message = "Industry can not be a null or empty")
    @Size(max = 50, message = "The length of the industry should not be bigger than 50 chars")
    private String industry;

    @NotEmpty(message = "Location can not be a null or empty")
    @Size(max = 50, message = "The length of the location should not be bigger than 50 chars")
    private String location;

    @NotEmpty(message = "Website can not be a null or empty")
    @Size(max = 150, message = "The length of the website should not be bigger than 150 chars")
    private String website;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
