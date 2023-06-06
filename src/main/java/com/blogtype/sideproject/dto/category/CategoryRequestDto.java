package com.blogtype.sideproject.dto.category;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@ApiModel(description = "카테고리 관련 요청 관리 Dto")
public class CategoryRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestDto {

        @ApiModelProperty(position = 1 , value ="카테고리 명",required = true)
        private String categoryName;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ModifyCategoryDto {

        @ApiModelProperty(position = 1 , value ="카테고리 명",required = true)
        private String categoryName;

    }
}
