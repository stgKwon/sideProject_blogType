package com.blogtype.sideproject.repository.category;

import com.blogtype.sideproject.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoryRepository extends JpaRepository<Category,Long>, CategoryCustomRepository {

}
