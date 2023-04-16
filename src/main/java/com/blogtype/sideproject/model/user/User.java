package com.blogtype.sideproject.model.user;


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
    private Long userName;

    @Column
    private Long email;
}
