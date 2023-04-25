package com.blogtype.sideproject.model.user;


import com.blogtype.sideproject.dto.user.UserResponseDto;
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
    private Long kakaoId;

    @Column
    private String userName;

    @Column
    private String nickName;

    @Column
    private String email;

    @Column
    private String profileImgUrl;

    public static User createUser(UserResponseDto.KakaoUserInfo userInfo){
        return User.builder()
                .kakaoId(userInfo.getKakaoId())
                .userName(userInfo.getUserName())
                .email(userInfo.getEmail())
                .build();
    }
}
