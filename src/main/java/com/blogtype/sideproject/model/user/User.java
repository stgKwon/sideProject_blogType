package com.blogtype.sideproject.model.user;


import com.blogtype.sideproject.dto.user.UserRequestDto;
import com.blogtype.sideproject.dto.user.UserResponseDto;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "TB_USER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EnableJpaAuditing // Auditing 활성화
@EntityListeners(AuditingEntityListener.class) // Auditing 리스너 등록
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

    @Column(name = "mod_time")
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(name = "reg_time", updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;

    public static User createUser(UserResponseDto.KakaoUserInfo userInfo){
        return User.builder()
                .kakaoId(userInfo.getKakaoId())
                .userName(userInfo.getUserName())
                .profileImgUrl(userInfo.getProfileImg())
                .email(userInfo.getEmail())
                .build();
    }

    public void updateUser(UserRequestDto.ModifyUserDto userInfo){
        this.nickName = userInfo.getNickName();
        this.intro = userInfo.getIntro();
        this.profileImgUrl = userInfo.getUpdateImgUrl();
    }
}
