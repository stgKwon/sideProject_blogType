package com.blogtype.sideproject.dto.qna;


import com.blogtype.sideproject.model.qna.Qna;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  QnA 글 관리
 */
@ApiModel(description = "QnA 관련 요청 관리 Dto")
public class QnaResponseDto {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto implements Serializable {

        @ApiModelProperty(position = 1, example = "1", required = true)
        private Long qnaId;

        @ApiModelProperty(position = 2, example = "1", required = true)
        private Long userId;

        @ApiModelProperty(position = 3, example = "제목", required = true)
        private String qnaTitle;

        @ApiModelProperty(position = 4, example = "내용", required = true)
        private String contents;

        public QnaResponseDto.ResponseDto qnaConvertToDto(Qna qna){
            return ResponseDto.builder()
                    .qnaId(qna.getId())
                    .userId(qna.getUserId())
                    .qnaTitle(qna.getQnaTitle())
                    .contents(qna.getContents())
                    .build();
        }

        public List<QnaResponseDto.ResponseDto> qnaConvertToDtoList(List<Qna> qnaList) {
            List<QnaResponseDto.ResponseDto> qnaDtoList = new ArrayList<>();

            for (Qna qna : qnaList) {
                QnaResponseDto.ResponseDto qnaDto = QnaResponseDto.ResponseDto.builder()
                        .qnaId(qna.getId())
                        .userId(qna.getUserId())
                        .qnaTitle(qna.getQnaTitle())
                        .contents(qna.getContents())
                        .build();
                qnaDtoList.add(qnaDto);
            }
            return qnaDtoList;
        }

    }
}
