package com.blogtype.sideproject.service.category.Impl;

import com.blogtype.sideproject.dto.category.CategoryRequestDto;
import com.blogtype.sideproject.dto.category.CategoryResponseDto;
import com.blogtype.sideproject.model.category.Category;
import com.blogtype.sideproject.repository.category.CategoryRepository;
import com.blogtype.sideproject.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryResponseDto.ResponseDto> findAllCategoryList(Long userId) throws Exception {
        List<CategoryResponseDto.ResponseDto> resultList = new ArrayList<>();
        try {
            Optional<List<Category>> optionalCategoryList = categoryRepository.findAllCategoryList(userId);
            if (optionalCategoryList.isPresent()) {
                List<Category> findAllCategoryList = optionalCategoryList.get();
                resultList = new CategoryResponseDto.ResponseDto().categoryConvertToDtoList(findAllCategoryList,userId);
            }

        }catch (Exception e){
            log.error("[CategoryService] findAllCategoryList :: " , e);
            throw new Exception();
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDto.ResponseDto findCategory(Long userId, Long categoryId) throws Exception {
        CategoryResponseDto.ResponseDto result = new CategoryResponseDto.ResponseDto();
        try{
            Optional<Category> optionalCategory = categoryRepository.findCategory(categoryId,userId);
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                result = new CategoryResponseDto.ResponseDto().categoryConvertToDto(category, userId);
            }
        }catch (Exception e){
            log.error("[CategoryService] findCategory :: " , e);
            throw new Exception();
        }
        return result;
    }

    @Override
    public void createCategory(Long userId, CategoryRequestDto.RequestDto requestDto) throws Exception {

        try{
            Category category = Category.createCategory(userId,requestDto);
            categoryRepository.save(category);
        }catch (Exception e){
            log.error("[CategoryService] createCategory :: " , e);
            throw new Exception();
        }
    }
    @Override
    public void modifyCategory(Long userId , Long categoryId , CategoryRequestDto.ModifyCategoryDto requestDto ) throws Exception {

        try{
            Optional<Category> optionalCategory = categoryRepository.findCategory(userId, categoryId);
            if (optionalCategory.isPresent()){
                Category category = optionalCategory.get();
                category.updateCategory(requestDto);
            }

        }catch (Exception e){
            log.error("[BoardService] modifyBoard :: " , e);
            throw new Exception();
        }
    }

    @Override
    public void deleteCategory(Long userId , Long categoryId) throws Exception {

        try{
            Optional<Category> optionalCategory = categoryRepository.findCategory(userId, categoryId);
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                categoryRepository.delete(category);
            }

            }catch (Exception e){
            log.error("[BoardService] deleteCategory :: " , e);
            throw new Exception();
        }
    }
}
