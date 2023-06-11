package com.blogtype.sideproject.repository.user;

import com.blogtype.sideproject.dto.user.UserResponseDto;
import com.blogtype.sideproject.model.user.User;

import javax.persistence.TypedQuery;
import java.util.List;
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


    Optional<List<UserResponseDto.ResponseWriteDate>> findWriteDateByUser(Long userId, String startDate , String endDate);

}
