package com.blogtype.sideproject.dto.user;

import com.blogtype.sideproject.model.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(description = "유저 관련 응답 관리 Dto")
public class UserResponseDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class TokenInfo implements Serializable {

        @ApiModelProperty(position = 1 , value ="유저 idx",required = true)
        private Long userId;
        @ApiModelProperty(position = 2 , value ="접근 토큰",required = true)
        private String accessToken;

        public TokenInfo setAccessToken(Long userId,String accessToken){
            return TokenInfo.builder()
                    .userId(userId)
                    .accessToken(accessToken).build();
        }

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class UserInfo implements Serializable {

        @ApiModelProperty(position = 1 , value ="유저 idx",required = true)
        private Long userId;

        @ApiModelProperty(position = 2 , value ="유저이름",required = true)
        private String userName;

        @ApiModelProperty(position = 3 , value ="닉네임",required = true)
        private String nickName;

        @ApiModelProperty(position = 4 , value ="프로필 이미지 URL",required = true)
        private String profileImgUrl;

        @ApiModelProperty(position = 5 , value ="자기소개",required = true)
        private String intro;

        public UserResponseDto.UserInfo userConvertToDto(User user){
            return UserResponseDto.UserInfo.builder()
                    .userId(user.getId())
                    .userName(user.getUserName())
                    .nickName(user.getNickName())
                    .profileImgUrl(user.getProfileImgUrl())
                    .intro(user.getIntro())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class KakaoUserInfo {

        @ApiModelProperty(position = 1 , example ="110110",required = true)
        private Long kakaoId;
        @ApiModelProperty(position = 2 , example ="유저이름",required = true)
        private String userName;
        @ApiModelProperty(position = 3 , example ="프로필 이미지",required = true)
        private String profileImg;
        @ApiModelProperty(position = 4 , example ="email@email.com",required = true)
        private String email;

        public UserResponseDto.KakaoUserInfo setUserInfo(Long kakaoId, String userName, String profileImg, String email){
            return UserResponseDto.KakaoUserInfo.builder()
                    .kakaoId(kakaoId)
                    .userName(userName)
                    .profileImg(profileImg)
                    .email(email)
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class WriteDateResponseDto implements Serializable{

        @ApiModelProperty(position = 1 , example ="20230518",required = false)
        private String writeDate;

        @ApiModelProperty(position = 2 , example ="20",required = false)
        private String totalCnt;
    }



}
