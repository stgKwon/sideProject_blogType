package com.blogtype.sideproject.dto.category;

import com.blogtype.sideproject.dto.board.BoardResponseDto;
import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.model.category.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@ApiModel(description = "카테고리 관련 응답 관리 Dto")
public class CategoryResponseDto {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto {

        @ApiModelProperty(position = 1 , example ="1",required = true)
        private Long categoryId;

        @ApiModelProperty(position = 2 , example ="1",required = true)
        private Long userId;

        @ApiModelProperty(position = 3, example ="카테고리 명",required = true)
        private String categoryName;

        @ApiModelProperty(position = 4 , example = "해당 카테고리 게시판 목록 (boardList)",required = true)
        private List<BoardResponseDto.ResponseDto> boardList = new ArrayList<>();

        public CategoryResponseDto.ResponseDto categoryConvertToDto(Category category, Long userId){
            return ResponseDto.builder()
                    .categoryId(category.getId())
                    .userId(userId)
                    .categoryName(category.getCategoryName())
                    .boardList(mapBoardList(category.getBoardList()))
                    .build();
        }

        public List<CategoryResponseDto.ResponseDto> categoryConvertToDtoList(List<Category> categoryList, Long userId) {
            List<CategoryResponseDto.ResponseDto> categoryDtoList = new ArrayList<>();
            for (Category category : categoryList) {
                List<BoardResponseDto.ResponseDto> boardDtoList = mapBoardList(category.getBoardList());

                CategoryResponseDto.ResponseDto categoryDto = CategoryResponseDto.ResponseDto.builder()
                        .categoryId(category.getId())
                        .userId(userId)
                        .categoryName(category.getCategoryName())
                        .boardList(boardDtoList)
                        .build();
                categoryDtoList.add(categoryDto);
            }
            return categoryDtoList;
        }

        private List<BoardResponseDto.ResponseDto> mapBoardList(List<Board> boardList) {
            List<BoardResponseDto.ResponseDto> boardDtoList = new ArrayList<>();
            if (boardList != null && !boardList.isEmpty()) {
                for (Board board : boardList) {
                    BoardResponseDto.ResponseDto boardDto = BoardResponseDto.ResponseDto.builder()
                            .boardId(board.getId())
                            .categoryId(board.getCategory() != null ? board.getCategory().getId() : null)
                            .boardTitle(board.getBoardTitle())
                            .contents(board.getContents())
                            .build();
                    boardDtoList.add(boardDto);
                }
            }
            return boardDtoList;
        }
    }



}
