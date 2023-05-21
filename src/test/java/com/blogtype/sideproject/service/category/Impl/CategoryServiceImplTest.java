package com.blogtype.sideproject.service.category.Impl;

import com.blogtype.sideproject.dto.category.CategoryResponseDto;
import com.blogtype.sideproject.model.category.Category;
import com.blogtype.sideproject.repository.category.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
class CategoryServiceImplTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    @Test
    @Transactional
    void findAllCategoryList() {

        List<CategoryResponseDto.ResponseDto> resultList = new ArrayList<>();
        try {
            Long userId = 1L;
            Optional<List<Category>> optionalCategoryList = categoryRepository.findAllCategoryList(userId);
            if (optionalCategoryList.isPresent()) {
                List<Category> findAllCategoryList = optionalCategoryList.get();
                resultList = new CategoryResponseDto.ResponseDto().categoryConvertToDtoList(findAllCategoryList,userId);
            }
            resultList.forEach(f -> {
                System.out.println("야야야야ㅑ야야야야야"+ f.getCategoryName());
            });

        }catch (Exception e){
            System.out.println("[CategoryService] findAllCategoryList :: " + e.getCause());
        }

    }

    @Test
    void findCategory() {
    }

    @Test
    void createCategory() {
    }

    @Test
    void modifyCategory() {
    }

    @Test
    void deleteCategory() {
    }
}