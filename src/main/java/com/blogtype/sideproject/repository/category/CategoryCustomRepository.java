package com.blogtype.sideproject.repository.category;


import com.blogtype.sideproject.model.category.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryCustomRepository {


    Optional<List<Category>> findAllCategoryList(Long userId);

    Optional<Category> findCategory(Long userId, Long categoryId);

}
