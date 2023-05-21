package com.blogtype.sideproject.model.board;

import com.blogtype.sideproject.dto.board.BoardRequestDto;
import com.blogtype.sideproject.dto.board.BoardResponseDto;
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

    public static Board createBoard(Long userId , BoardRequestDto.RequestDto requestDto){
        return Board.builder()
                .boardTitle(requestDto.getBoardTitle())
                .contents(requestDto.getContents())
                .userId(userId)
                .build();
    }

    public void updateBoard(BoardRequestDto.ModifyBoardDto requestDto){
        this.boardTitle = requestDto.getBoardTitle();
        this.contents = requestDto.getContents();
    }

}
