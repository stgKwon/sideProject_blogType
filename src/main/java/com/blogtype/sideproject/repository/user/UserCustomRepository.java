package com.blogtype.sideproject.repository.user;

import com.blogtype.sideproject.model.user.User;

import java.util.Optional;

public interface UserCustomRepository {

    /*
    DESC :: 단일 유저 정보 조회
    */
    Optional<User> findUser(Long userId);

    /*
    DESC :: 단일 유저 정보 (카카오) 조회
    */
    Optional<User> findUserByKakaoId(Long kakaoId);

}
