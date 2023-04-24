package com.blogtype.sideproject.service.user;

import com.blogtype.sideproject.dto.user.UserDTO;

public interface UserService {

    /**
     * DESC :: 카카오톡 로그인 및 토큰 발급
     */
    UserDTO.ResponseDto kakaoLogin(String code) throws Exception;

    /**
     * DESC :: 회원 정보 조회
     */
    UserDTO.ResponseUserInfo findUserInfo(Long userId) throws Exception;

}
