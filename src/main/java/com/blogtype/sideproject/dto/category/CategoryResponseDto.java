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
    public static class ResponseCategory {

        @ApiModelProperty(position = 1 , value ="카테고리 idx",required = true)
        private Long categoryId;

        @ApiModelProperty(position = 2 , value ="유저 idx",required = true)
        private Long userId;

        @ApiModelProperty(position = 3, value ="카테고리 명",required = true)
        private String categoryName;

        @ApiModelProperty(position = 4 , value = "해당 카테고리 게시판 목록 (boardList)",required = true)
        private List<BoardResponseDto.ResponseBoard> boardList = new ArrayList<>();

        public CategoryResponseDto.ResponseCategory categoryConvertToDto(Category category, Long userId){
            return ResponseCategory.builder()
                    .categoryId(category.getId())
                    .userId(userId)
                    .categoryName(category.getCategoryName())
                    .boardList(mapBoardList(category.getBoardList(),userId))
                    .build();
        }

        public List<CategoryResponseDto.ResponseCategory> categoryConvertToDtoList(List<Category> categoryList, Long userId) {
            List<CategoryResponseDto.ResponseCategory> categoryDtoList = new ArrayList<>();
            for (Category category : categoryList) {
                List<BoardResponseDto.ResponseBoard> boardDtoList = mapBoardList(category.getBoardList(),userId);

                CategoryResponseDto.ResponseCategory categoryDto = CategoryResponseDto.ResponseCategory.builder()
                        .categoryId(category.getId())
                        .userId(userId)
                        .categoryName(category.getCategoryName())
                        .boardList(boardDtoList)
                        .build();
                categoryDtoList.add(categoryDto);
            }
            return categoryDtoList;
        }

        private List<BoardResponseDto.ResponseBoard> mapBoardList(List<Board> boardList, Long userId) {
            List<BoardResponseDto.ResponseBoard> boardDtoList = new ArrayList<>();
            if (boardList != null && !boardList.isEmpty()) {
                for (Board board : boardList) {
                    BoardResponseDto.ResponseBoard boardDto = BoardResponseDto.ResponseBoard.builder()
                            .boardId(board.getId())
                            .categoryId(board.getCategory() != null ? board.getCategory().getId() : null)
                            .userId(userId)
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
