package com.blogtype.sideproject.model.user;


import com.blogtype.sideproject.dto.user.UserRequestDto;
import com.blogtype.sideproject.dto.user.UserResponseDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    @NotNull
    private Long kakaoId;

    @Column
    @NotNull
    private String userName;

    @Column
    private String nickName;

    @Column
    private String email;

    @Column
    private String profileImgUrl;

    @Column
    private String intro;


    public static User createUser(UserResponseDto.KakaoUserInfo userInfo){
        return User.builder()
                .kakaoId(userInfo.getKakaoId())
                .userName(userInfo.getUserName())
                .profileImgUrl(userInfo.getProfileImg())
                .email(userInfo.getEmail())
                .build();
    }

    public void updateUser(UserRequestDto.ModifyUser userInfo){
        this.nickName = userInfo.getNickName();
        this.intro = userInfo.getIntro();
        this.profileImgUrl = userInfo.getUpdateImgUrl();
    }
}
