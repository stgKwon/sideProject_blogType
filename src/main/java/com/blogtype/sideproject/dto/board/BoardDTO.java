package com.blogtype.sideproject.dto.board;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  BLOG 게시글 관리
 */
@ApiModel(description = "게시글 관련 요청/ 응답 관리 Dto")
public class BoardDTO {

    //FIXME :: 요청, 응답부 분리 예정

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
