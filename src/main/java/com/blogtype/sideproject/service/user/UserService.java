package com.blogtype.sideproject.service.user;

import com.blogtype.sideproject.dto.user.UserRequestDto;
import com.blogtype.sideproject.dto.user.UserResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    /**
     * DESC :: 카카오톡 로그인 및 토큰 발급
     */
    UserResponseDto.TokenInfo kakaoLogin(String code) throws Exception;

    /**
     * DESC :: 회원 정보 조회
     */
    UserResponseDto.UserInfo findUserInfo(Long userId) throws Exception;

    /**
     * DESC :: 회원 정보 수정
     */
    void modifyUser(Long userId , UserRequestDto.ModifyUser requestDto, MultipartFile imgFile) throws Exception;

    /**
     * DESC :: 회원이 글을 작성한 일자와 카운트
     */
    UserResponseDto.ResponseWriteDate findWriteDateByUser(Long userId, UserRequestDto.RequestWriteDate requestDto) throws Exception;
}
