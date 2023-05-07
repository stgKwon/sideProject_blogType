package com.blogtype.sideproject.model.board;

import com.blogtype.sideproject.dto.board.BoardDto;
import com.blogtype.sideproject.model.category.Category;
import com.sun.istack.NotNull;
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
    @NotNull
    private String boardTitle;

    @Column
    private String contents;

    @Column
    @NotNull
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    //FIXME :: 확인필요
    public void setCategory(Category category) {
        if (this.category != null) {
            this.category.getBoardList().remove(this);
        }
        this.category = category;
        if (category != null) {
            category.getBoardList().add(this);
        }
    }

    public static Board createBoard(Long userId , BoardDto.RequestDto requestDto){
        return Board.builder()
                .boardTitle(requestDto.getBoardTitle())
                .contents(requestDto.getContents())
                .userId(userId)
                .build();
    }
}
