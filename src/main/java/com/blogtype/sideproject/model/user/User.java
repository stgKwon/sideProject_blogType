package com.blogtype.sideproject.model.user;


import com.blogtype.sideproject.dto.user.UserDTO;
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
    private String email;

    public static User createUser(UserDTO.KakaoUserInfo userInfo){
        return User.builder()
                .kakaoId(userInfo.getKakaoId())
                .userName(userInfo.getUserName())
                .email(userInfo.getEmail())
                .build();
    }
}
