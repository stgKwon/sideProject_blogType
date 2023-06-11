package com.blogtype.sideproject.dto.qna;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(description = "QnA 관련 요청 관리 Dto")
public class QnaRequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestQna {

        @ApiModelProperty(position = 1 , value ="qna 제목",required = true)
        private String qnaTitle;

        @ApiModelProperty(position = 2 , value ="qna 내용",required = true)
        private String contents;
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ModifyQna {

        @ApiModelProperty(position = 1 , value ="qna 제목",required = true)
        private String qnaTitle;

        @ApiModelProperty(position = 2 , value ="qna 내용",required = true)
        private String contents;
    }
}
