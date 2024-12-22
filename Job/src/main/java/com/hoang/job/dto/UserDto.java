package com.hoang.job.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data @Builder
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    private String name;

    private String gender;

    private int age;

    private String email;

    private String phone;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
