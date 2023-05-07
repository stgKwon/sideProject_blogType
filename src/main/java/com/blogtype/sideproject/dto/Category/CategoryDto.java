package com.blogtype.sideproject.dto.Category;

import com.blogtype.sideproject.dto.board.BoardDto;
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

        @ApiModelProperty(position = 1 , example ="1",required = true)
        private Long categoryId;

        @ApiModelProperty(position = 2 , example ="1",required = true)
        private Long userId;

        @ApiModelProperty(position = 3, example ="카테고리",required = true)
        private String categoryName;

        @ApiModelProperty(position = 4 , example = "해당 카테고리 게시판 목록 (boardList)",required = true)
        private List<BoardDto.ResponseDto> boardList = new ArrayList<>();

        public CategoryDto.ResponseDto categoryConvertToDto(Category category, Long userId){
            return ResponseDto.builder()
                    .categoryId(category.getId())
                    .userId(userId)
                    .categoryName(category.getCategoryName())
                    .boardList(mapBoardList(category.getBoardList()))
                    .build();
        }

        public List<CategoryDto.ResponseDto> categoryConvertToDtoList(List<Category> categoryList, Long userId) {
            List<CategoryDto.ResponseDto> categoryDtoList = new ArrayList<>();
            for (Category category : categoryList) {
                List<BoardDto.ResponseDto> boardDtoList = mapBoardList(category.getBoardList());

                CategoryDto.ResponseDto categoryDto = CategoryDto.ResponseDto.builder()
                        .categoryId(category.getId())
                        .userId(userId)
                        .categoryName(category.getCategoryName())
                        .boardList(boardDtoList)
                        .build();
                categoryDtoList.add(categoryDto);
            }
            return categoryDtoList;
        }

        private List<BoardDto.ResponseDto> mapBoardList(List<Board> boardList) {
            List<BoardDto.ResponseDto> boardDtoList = new ArrayList<>();
            if (boardList != null && !boardList.isEmpty()) {
                for (Board board : boardList) {
                    BoardDto.ResponseDto boardDto = BoardDto.ResponseDto.builder()
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
