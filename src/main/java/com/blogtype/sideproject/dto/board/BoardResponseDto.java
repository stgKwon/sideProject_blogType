package com.blogtype.sideproject.dto.board;

import com.blogtype.sideproject.model.board.Board;
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
 *  BLOG 게시글 관리
 */
@ApiModel(description = "게시글 관련 요청/ 응답 관리 Dto")
public class BoardResponseDto {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseBoard implements Serializable {

        @ApiModelProperty(position = 1 , value ="게시글 idx",required = true)
        private Long boardId;

        @ApiModelProperty(position = 2 , value ="카테고리 idx",required = true)
        private Long categoryId;

        @ApiModelProperty(position = 3, value ="유저 idx",required = true)
        private Long userId;

        @ApiModelProperty(position = 4, value ="게시글 제목",required = true)
        private String boardTitle;

        @ApiModelProperty(position = 5 , value = "게시글 내용",required = true)
        private String contents;

        public BoardResponseDto.ResponseBoard boardConvertToDto(Board board){
            return BoardResponseDto.ResponseBoard.builder()
                    .boardId(board.getId())
                    .categoryId(board.getCategory() != null ? board.getCategory().getId() : null)
                    .userId(board.getUserId())
                    .boardTitle(board.getBoardTitle())
                    .contents(board.getContents())
                    .build();
        }

        public List<BoardResponseDto.ResponseBoard> boardConvertToDtoList(List<Board> boardList) {
            List<BoardResponseDto.ResponseBoard> boardDtoList = new ArrayList<>();

            for (Board board : boardList) {
                BoardResponseDto.ResponseBoard boardDto = BoardResponseDto.ResponseBoard.builder()
                        .boardId(board.getId())
                        .categoryId(board.getCategory() != null ? board.getCategory().getId() : null)
                        .userId(board.getUserId())
                        .boardTitle(board.getBoardTitle())
                        .contents(board.getContents())
                        .build();
                boardDtoList.add(boardDto);
            }
            return boardDtoList;
        }








    }


}
