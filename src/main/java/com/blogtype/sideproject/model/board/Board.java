package com.blogtype.sideproject.model.board;

import com.blogtype.sideproject.dto.board.BoardDto;
import com.blogtype.sideproject.model.category.Category;
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

    @Column(name = "categoryIdx")
    private Long categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private Category category;

    public static Board createBoard(Long userId , BoardDto.RequestDto requestDto){
        return Board.builder()
                .boardTitle(requestDto.getBoardTitle())
                .contents(requestDto.getContents())
                .categoryId(requestDto.getCategoryId())
                .userId(userId)
                .build();
    }
}
