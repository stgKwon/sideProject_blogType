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

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDto.ResponseDto> findAllCategoryList() throws Exception {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryDto.ResponseDto findCategory(Long userId, Long categoryId) throws Exception {
        CategoryDto.ResponseDto result = new CategoryDto.ResponseDto();
        try{
            Optional<Category> category = categoryRepository.findCategory(categoryId,userId);
            /* FIXME :: 매핑 테스트 필요 */
            result = modelMapper.map(category, CategoryDto.ResponseDto.class);

        }catch (Exception e){
            log.error("[CategoryService] findCategory :: " , e);
        }
        return result;
    }

    @Override
    public void createCategory(Long userId, CategoryDto.RequestDto requestDto) throws Exception {
        /*
            FIXME :: 해당 방식으로 처리했을 때의 문제를 생각해보자.
         */
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
