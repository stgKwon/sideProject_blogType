package com.blogtype.sideproject.dto.common;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "공통 응답 관리 Dto")
public class ResponseDto {

    private String Message;
    private Object data;
    private int status;
}
