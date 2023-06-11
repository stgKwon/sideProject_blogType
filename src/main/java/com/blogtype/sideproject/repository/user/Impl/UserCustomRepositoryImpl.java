package com.blogtype.sideproject.repository.user.Impl;


import com.amazonaws.services.s3.transfer.Copy;
import com.blogtype.sideproject.dto.user.UserResponseDto;
import com.blogtype.sideproject.model.user.User;
import com.blogtype.sideproject.repository.user.UserCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {

    private final EntityManager entityManager;

    @Override
    public Optional<User> findUser(Long userId) {
        return Optional.ofNullable(entityManager.createQuery("SELECT u FROM User AS u WHERE u.Id =:userId", User.class)
                .setParameter("userId",userId)
                .getSingleResult());
    }

    @Override
    public Optional<User> findUserByKakaoId(Long kakaoId) {
        return Optional.ofNullable(entityManager.createQuery("SELECT u FROM User AS u WHERE u.kakaoId =:kakaoId", User.class)
                .setParameter("kakaoId",kakaoId)
                .getSingleResult());
    }

    @Override
    public Optional<List<UserResponseDto.ResponseWriteDate>> findWriteDateByUser(Long userId, String startDate, String endDate) {

        return Optional.empty();
    }

}
