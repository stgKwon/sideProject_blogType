package com.blogtype.sideproject.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;

/**
 *  USER 관리
 */
public class UserDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class RequestDto {

        @ApiModelProperty(position = 1 , example ="idx",required = true)
        private Long id;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseDto implements Serializable {


        @ApiModelProperty(position = 1 , example ="idx",required = true)
        private String accessToken;

        public void setAccessToken(String accessToken){
            ResponseDto.builder().accessToken(accessToken).build();
        }

    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ResponseUserInfo implements Serializable {

        @ApiModelProperty(position = 1 , example ="idx",required = true)
        private Long userId;

        @ApiModelProperty(position = 2 , example ="userName",required = true)
        private String userName;

        @ApiModelProperty(position = 3 , example ="imageUrl",required = true)
        private String profileImgUrl;

        public void setUserInfo(Long userId, String userName, String profileImgUrl){
            ResponseUserInfo.builder()
                    .userId(userId)
                    .userName(userName)
                    .profileImgUrl(profileImgUrl)
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
        @ApiModelProperty(position = 2 , example ="user1",required = true)
        private String userName;
        @ApiModelProperty(position = 3 , example ="email@email.com",required = true)
        private String email;

        public void setUserInfo(Long kakaoId , String userName , String email){
            KakaoUserInfo.builder()
                    .kakaoId(kakaoId)
                    .userName(userName)
                    .email(email)
                    .build();
        }
    }
}
