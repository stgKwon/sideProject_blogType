package com.blogtype.sideproject.dto.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel(description = "유저 관련 요청 관리 Dto")
public class UserRequestDto {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ModifyUser {

        @ApiModelProperty(position = 1 , example ="유저 닉네임",required = false)
        private String nickName;

        @ApiModelProperty(position = 2 , example ="현재 프로필 이미지 URL",required = false)
        private String imgUrl;

        @ApiModelProperty(position = 3 , example ="자기소개",required = false)
        private String intro;

    }

}
