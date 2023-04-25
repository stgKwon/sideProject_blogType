package com.blogtype.sideproject.service.category;

import com.blogtype.sideproject.dto.Category.CategoryDto;
import com.blogtype.sideproject.dto.board.BoardDto;

import java.util.List;

public interface CategoryService {

    /**
     * DESC :: 카테고리 전체 목록 조회
     */
    List<CategoryDto.ResponseDto> findAllCategoryList() throws Exception;

    /**
     * DESC :: 카테고리 전체 목록 조회
     */
    CategoryDto.ResponseDto findCategory(Long userId, Long categoryId) throws Exception;

    /**
     * DESC :: 카테고리 게시글 생성
     */
    void createCategory(Long userId,  CategoryDto.RequestDto requestDto) throws Exception;

    /**
     * DESC :: 카테고리 게시글 수정
     */
    void modifyCategory() throws Exception;

    /**
     * DESC :: 카테고리 게시글 삭제
     */
    void deleteCategory() throws Exception;
}
