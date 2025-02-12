package com.hoang.job.dto;

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

@Data @Builder
public class JobEagerDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    @NotEmpty(message = "Title can not be a null or empty")
    @Size(max = 50, message = "The length of the title should not be bigger than 50 chars")
    private String title;

    @NotEmpty(message = "Description can not be a null or empty")
    @Size(max = 255, message = "The length of the description should not be bigger than 255 chars")
    private String description;

    @NotEmpty(message = "Requirement can not be a null or empty")
    @Size(max = 255, message = "The length of the requirement should not be bigger than 255 chars")
    private String requirement;

    @NotEmpty(message = "Salary can not be a null or empty")
    @Size(max = 50, message = "The length of the salary should not be bigger than 50 chars")
    private String salary;

    @NotEmpty(message = "Location can not be a null or empty")
    @Size(max = 50, message = "The length of the location should not be bigger than 50 chars")
    private String location;

    private LocalDateTime postedDate;

    @Valid
    private EmployerDto postedBy;

    private LocalDateTime expiryDate;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
