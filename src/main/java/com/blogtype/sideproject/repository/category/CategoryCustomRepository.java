package com.blogtype.sideproject.repository.category;


import com.blogtype.sideproject.model.category.Category;

import java.util.Optional;

public interface CategoryCustomRepository {

    Optional<Category> findCategory(Long categoryId, Long userId);

}
