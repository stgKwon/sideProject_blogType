package com.blogtype.sideproject.dto.board;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  BLOG 게시글 관리
 */
@ApiModel(description = "게시글 관련 요청 Dto")
public class BoardRequestDto {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestDto {

        @ApiModelProperty(position = 1 , value = "카테고리 idx",required = true)
        private Long categoryId;

        @ApiModelProperty(position = 2 , value = "게시글 제목",required = true)
        private String boardTitle;

        @ApiModelProperty(position = 3 , value = "게시글 내용",required = true)
        private String contents;
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ModifyBoardDto {

        @ApiModelProperty(position = 1 , value = "게시글 제목",required = true)
        private String boardTitle;

        @ApiModelProperty(position = 2 , value = "게시글 내용",required = true)
        private String contents;
    }
}
