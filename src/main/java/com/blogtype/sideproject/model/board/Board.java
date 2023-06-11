package com.blogtype.sideproject.model.board;

import com.blogtype.sideproject.dto.board.BoardRequestDto;
import com.blogtype.sideproject.dto.board.BoardResponseDto;
import com.blogtype.sideproject.model.category.Category;
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
@Table(name = "TB_BOARD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EnableJpaAuditing // Auditing 활성화
@EntityListeners(AuditingEntityListener.class) // Auditing 리스너 등록
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JoinColumn
    private Category category;

    @Column
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;

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

    public static Board createBoard(Long userId , BoardRequestDto.RequestBoard requestDto){
        return Board.builder()
                .boardTitle(requestDto.getBoardTitle())
                .contents(requestDto.getContents())
                .userId(userId)
                .build();
    }

    public void updateBoard(BoardRequestDto.ModifyBoard requestDto){
        this.boardTitle = requestDto.getBoardTitle();
        this.contents = requestDto.getContents();
    }

}
