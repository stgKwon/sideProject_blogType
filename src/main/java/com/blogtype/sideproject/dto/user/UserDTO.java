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
        private Long id;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class kakaoUserInfo {

        @ApiModelProperty(position = 1 , example ="110110",required = true)
        private Long kakaoId;
        @ApiModelProperty(position = 2 , example ="user1",required = true)
        private String userName;
        @ApiModelProperty(position = 3 , example ="email@email.com",required = true)
        private String email;

        public UserDTO.kakaoUserInfo setUserInfo(Long kakaoId , String userName , String email){
           return UserDTO.kakaoUserInfo.builder()
                    .kakaoId(kakaoId)
                    .userName(userName)
                    .email(email)
                    .build();
        }
    }
}
