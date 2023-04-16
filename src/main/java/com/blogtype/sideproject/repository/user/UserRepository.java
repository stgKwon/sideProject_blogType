package com.blogtype.sideproject.repository.user;

import com.blogtype.sideproject.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>, UserCustomRepository {

    // 단순 값 하나를 조회
    @Query("select u from User u where u.kakaoId = :kakaoId")
    Optional<User> findAllByKakaoId(Long kakaoId);
}
