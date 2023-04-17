package com.blogtype.sideproject.model.board;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String boardTitle;

    @Column
    private Long contents;

    @Column
    private Long userId;
}
