package com.blogtype.sideproject.service.category.Impl;

import com.blogtype.sideproject.dto.Category.CategoryDto;
import com.blogtype.sideproject.dto.board.BoardDto;
import com.blogtype.sideproject.model.category.Category;
import com.blogtype.sideproject.repository.category.CategoryRepository;
import com.blogtype.sideproject.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    public List<CategoryDto.ResponseDto> findAllCategoryList(Long userId) throws Exception {
        List<CategoryDto.ResponseDto> resultList = new ArrayList<>();
        try {
            Optional<List<Category>> optionalCategoryList = categoryRepository.findAllCategoryList(userId);
            if (optionalCategoryList.isPresent()) {
                List<Category> findAllCategoryList = optionalCategoryList.get();
                resultList = new CategoryDto.ResponseDto().categoryConvertToDtoList(findAllCategoryList,userId);
            }

        }catch (Exception e){
            log.error("[CategoryService] findAllCategoryList :: " , e);
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto.ResponseDto findCategory(Long userId, Long categoryId) throws Exception {
        CategoryDto.ResponseDto result = new CategoryDto.ResponseDto();
        try{
            Optional<Category> optionalCategory = categoryRepository.findCategory(categoryId,userId);
            if (optionalCategory.isPresent()) {
                Category category = optionalCategory.get();
                result = new CategoryDto.ResponseDto().categoryConvertToDto(category, userId);
            }
        }catch (Exception e){
            log.error("[CategoryService] findCategory :: " , e);
        }
        return result;
    }

    @Override
    public void createCategory(Long userId, CategoryDto.RequestDto requestDto) throws Exception {

        try{
            Category category = Category.createCategory(userId,requestDto);
            categoryRepository.save(category);
        }catch (Exception e){
            log.error("[CategoryService] createCategory :: " , e);
        }
    }
    @Override
    public void modifyCategory() throws Exception {

    }

    @Override
    public void deleteCategory() throws Exception {

    }
}
