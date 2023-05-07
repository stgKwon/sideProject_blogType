package com.blogtype.sideproject.dto.board;

import com.blogtype.sideproject.dto.Category.CategoryDto;
import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.model.category.Category;
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
public class BoardDto {

    //FIXME :: 요청, 응답부 분리 예정

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestDto {

        @ApiModelProperty(position = 1 , example ="1",required = true)
        private Long categoryId;

        @ApiModelProperty(position = 2 , example ="제목",required = true)
        private String boardTitle;

        @ApiModelProperty(position = 3 , example ="내용",required = true)
        private String contents;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto implements Serializable {

        @ApiModelProperty(position = 1 , example ="1",required = true)
        private Long boardId;

        @ApiModelProperty(position = 2 , example ="1",required = true)
        private Long categoryId;

        @ApiModelProperty(position = 3, example ="1",required = true)
        private Long userId;

        @ApiModelProperty(position = 4, example ="제목",required = true)
        private String boardTitle;

        @ApiModelProperty(position = 5 , example = "내용",required = true)
        private String contents;

        public BoardDto.ResponseDto boardConvertToDto(Board board){
            return BoardDto.ResponseDto.builder()
                    .boardId(board.getId())
                    .categoryId(board.getCategory() != null ? board.getCategory().getId() : null)
                    .userId(board.getUserId())
                    .boardTitle(board.getBoardTitle())
                    .contents(board.getContents())
                    .build();
        }

        public List<BoardDto.ResponseDto> boardConvertToDtoList(List<Board> boardList) {
            List<BoardDto.ResponseDto> boardDtoList = new ArrayList<>();

            for (Board board : boardList) {
                BoardDto.ResponseDto boardDto = BoardDto.ResponseDto.builder()
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
