package com.hoang.jobapplication.dto;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class ResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String statusCode;

    private String statusMsg;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
