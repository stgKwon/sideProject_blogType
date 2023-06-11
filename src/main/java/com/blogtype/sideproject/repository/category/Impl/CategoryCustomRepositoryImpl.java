package com.blogtype.sideproject.repository.category.Impl;


import com.blogtype.sideproject.model.category.Category;
import com.blogtype.sideproject.repository.category.CategoryCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

    private final EntityManager entityManager;

    @Override
    public Optional<List<Category>> findAllCategoryList(Long userId) {
        return Optional.ofNullable(entityManager.createQuery("SELECT c FROM Category AS c WHERE c.userId =:userId", Category.class)
                .setParameter("userId",userId)
                .getResultList());
    }

    @Override
    public Optional<Category> findCategory(Long userId, Long categoryId) {
        return Optional.ofNullable(entityManager.createQuery("SELECT c FROM Category AS c WHERE c.Id =:categoryId AND c.userId =:userId", Category.class)
                .setParameter("userId",userId)
                .setParameter("categoryId",categoryId)
                .getSingleResult());
    }
}
