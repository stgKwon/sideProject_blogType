package com.blogtype.sideproject.controller;


import com.blogtype.sideproject.dto.category.CategoryRequestDto;
import com.blogtype.sideproject.dto.category.CategoryResponseDto;
import com.blogtype.sideproject.dto.common.ResponseDto;
import com.blogtype.sideproject.service.category.CategoryService;
import com.blogtype.sideproject.util.convert.ConvertUtil;
import com.blogtype.sideproject.util.response.ResponseUtils;
import com.blogtype.sideproject.util.security.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(tags = {"CATEGORY API"})
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;
    private static final ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();


    @GetMapping("/list/{userId}")
    @ApiOperation(value = "카테고리 전체 목록 조회" , notes = "전체 카테고리 내용을 조회한다.")
    public ResponseEntity<ResponseDto> findAllCategoryList(@PathVariable(name = "userId") Long userId) throws Exception {
        List<CategoryResponseDto.ResponseCategory> resultList = categoryService.findAllCategoryList(userId);
        return ResponseUtils.ok(resultList);
    }

    @GetMapping("/detail/{categoryId}")
    @ApiOperation(value = "카테고리 게시글 조회" , notes = "선택 카테고리 내용을 조회한다.")
    public ResponseEntity<ResponseDto> findCategory(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "categoryId") Long categoryId) throws Exception {
        CategoryResponseDto.ResponseCategory result = categoryService.findCategory(ConvertUtil.findUserId(userDetails), categoryId);
        return ResponseUtils.ok(result);
    }

    @PostMapping("/create")
    @ApiOperation(value = "카테고리 생성" , notes = "카테고리 목록을 생성한다.")
    public ResponseEntity<ResponseDto> createCategory(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CategoryRequestDto.RequestCategory categoryRequestDto) throws Exception {
        log.info("[CategoryController] createCategory_checkUserId: " + userDetails.getUser().getId());
        log.info("[CategoryController] createCategory_checkDto: " + writer.writeValueAsString(categoryRequestDto));
        categoryService.createCategory(ConvertUtil.findUserId(userDetails), categoryRequestDto);
        return ResponseUtils.ok(null);
    }

    @PatchMapping("/modify/{categoryId}")
    @ApiOperation(value = "카테고리 게시글 수정 " , notes = "선택 카테고리 내용을 수정한다.")
    public ResponseEntity<ResponseDto> modifyCategory(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "categoryId") Long categoryId, CategoryRequestDto.ModifyCategory categoryModRequestDto) throws Exception {
        log.info("[CategoryController] modifyBoard_checkUserId: " + userDetails.getUser().getId());
        categoryService.modifyCategory(ConvertUtil.findUserId(userDetails),categoryId, categoryModRequestDto);
        return ResponseUtils.ok(null);
    }

    @DeleteMapping("/delete/{categoryId}")
    @ApiOperation(value = "카테고리 게시글 삭제 " , notes = "선택 카테고리 내용을 삭제한다." )
    public ResponseEntity<ResponseDto> deleteCategory(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "categoryId") Long categoryId) throws Exception {
        log.info("[CategoryController] deleteCategory_checkUserId: " + userDetails.getUser().getId());
        categoryService.deleteCategory(ConvertUtil.findUserId(userDetails),categoryId);
        return ResponseUtils.ok(null);
    }

}
