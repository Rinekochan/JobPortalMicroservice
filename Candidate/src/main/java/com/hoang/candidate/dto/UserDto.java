package com.hoang.candidate.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    @NotEmpty(message = "Name can not be a null or empty")
    @Size(max = 100, message = "The length of the customer name should not be bigger than 100 chars")
    private String name;

    @Size(max = 20, message = "The length of the gender should not be bigger than 20 chars")
    private String gender;

    @Positive(message = "Your age should be positive")
    private int age;

    @Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @NotEmpty(message = "Email can not be a null or empty")
    private String email;

    @NotEmpty(message = "Mobile number can not be a null or empty")
    private String phone;

    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
