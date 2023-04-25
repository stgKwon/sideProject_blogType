package com.blogtype.sideproject.dto.Category;

import com.blogtype.sideproject.dto.board.BoardDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "카테고리 관련 요청 관리 Dto")
public class CategoryDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestDto {

        @ApiModelProperty(position = 1 , example ="유저 닉네임",required = false)
        private String categoryName;

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto {

        @ApiModelProperty(position = 1 , example ="idx",required = true)
        private Long categoryId;

        @ApiModelProperty(position = 2 , example ="idx",required = true)
        private Long userId;

        @ApiModelProperty(position = 3, example ="카테고리",required = true)
        private String categoryName;

        @ApiModelProperty(position = 4 , example = "해당 카테고리 게시판 목록 (boardList)",required = true)
        private List<BoardDto.ResponseDto> boardList = new ArrayList<>();

    }
}
