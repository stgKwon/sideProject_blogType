package com.blogtype.sideproject.service.category;

import com.blogtype.sideproject.dto.category.CategoryRequestDto;
import com.blogtype.sideproject.dto.category.CategoryResponseDto;

import java.util.List;

public interface CategoryService {

    /**
     * DESC :: 카테고리 전체 목록 조회
     */
    List<CategoryResponseDto.ResponseDto> findAllCategoryList(Long userId) throws Exception;

    /**
     * DESC :: 카테고리 전체 목록 조회
     */
    CategoryResponseDto.ResponseDto findCategory(Long userId, Long categoryId) throws Exception;

    /**
     * DESC :: 카테고리 게시글 생성
     */
    void createCategory(Long userId,  CategoryRequestDto.RequestDto requestDto) throws Exception;

    /**
     * DESC :: 카테고리 게시글 수정
     */
    void modifyCategory(Long userId , Long categoryId , CategoryRequestDto.ModifyCategoryDto requestDto) throws Exception;

    /**
     * DESC :: 카테고리 게시글 삭제
     */
    void deleteCategory(Long userId , Long categoryId) throws Exception;
}
