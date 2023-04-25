package com.blogtype.sideproject.repository.category.Impl;


import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.model.category.Category;
import com.blogtype.sideproject.repository.category.CategoryCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.Optional;


@Repository
@Transactional
@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

    private final EntityManager entityManager;

    @Override
    public Optional<Category> findCategory(Long categoryId, Long userId) {
        return Optional.ofNullable(entityManager.createQuery("SELECT c FROM Category AS c WHERE c.Id =:categoryId AND c.userId =:usreId", Category.class).getSingleResult());
    }
}
