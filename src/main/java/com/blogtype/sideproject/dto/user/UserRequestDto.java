package com.blogtype.sideproject.dto.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@ApiModel(description = "유저 관련 요청 관리 Dto")
public class UserRequestDto {


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ModifyUserDto {

        @ApiModelProperty(position = 1 , example ="유저 닉네임",required = false)
        private String nickName;

        @ApiModelProperty(position = 2 , example ="자기소개",required = false)
        private String intro;

        @ApiModelProperty(position = 3 , example ="업데이트 이미지 URL",required = false)
        private String updateImgUrl;

        @ApiModelProperty(position = 4 , example ="삭제 이미지 URL(변경 전 이미지 URL)",required = false)
        private String deleteImgUrl;

        public void setUpdateImgUrl(String updateImgUrl){
            this.updateImgUrl = updateImgUrl;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class WriteDateRequestDto {

        @ApiModelProperty(position = 1 , example ="20230518",required = false)
        private String startDate;

        @ApiModelProperty(position = 2 , example ="20230519",required = false)
        private String endDate;


    }

}
