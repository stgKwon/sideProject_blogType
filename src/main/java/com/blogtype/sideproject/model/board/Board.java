package com.blogtype.sideproject.model.board;

import com.blogtype.sideproject.dto.board.BoardDTO;
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
    private String contents;

    @Column
    private Long userId;


    public static Board createBoard(Long userId , BoardDTO.RequestDto requestDto){
        return Board.builder()
                .boardTitle(requestDto.getBoardTitle())
                .contents(requestDto.getContents())
                .userId(userId)
                .build();
    }
}
