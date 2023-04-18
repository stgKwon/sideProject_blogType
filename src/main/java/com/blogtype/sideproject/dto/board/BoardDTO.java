package com.blogtype.sideproject.dto.board;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  BLOG 게시글 관리
 */
public class BoardDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestDto {

        @ApiModelProperty(position = 1 , example ="제목",required = true)
        private String boardTitle;

        @ApiModelProperty(position = 2 , example ="내용",required = true)
        private String contents;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto implements Serializable {

        @ApiModelProperty(position = 1 , example ="idx",required = true)
        private Long boardId;

        @ApiModelProperty(position = 2, example ="제목",required = true)
        private Long boardTitle;

        @ApiModelProperty(position = 3 , example = "내용",required = true)
        private Long contents;
    }


}
