package com.hoang.jobapplication.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data @Builder
public class JobDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    private String title;

    private String description;

    private String requirement;

    private String salary;

    private String location;

    private LocalDateTime postedDate;

    private EmployerDto postedBy;

    private LocalDateTime expiryDate;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
