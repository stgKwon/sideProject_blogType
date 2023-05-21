package com.blogtype.sideproject.model.qna;


import com.blogtype.sideproject.dto.qna.QnaRequestDto;
import com.blogtype.sideproject.dto.qna.QnaResponseDto;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TB_QNA")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Qna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    @NotNull
    private String qnaTitle;

    @Column
    private String contents;

    @Column
    @NotNull
    private Long userId;

    public static Qna createQna(Long userId , QnaRequestDto.RequestDto requestDto){
        return Qna.builder()
                .qnaTitle(requestDto.getQnaTitle())
                .contents(requestDto.getContents())
                .userId(userId)
                .build();
    }

    public void updateQna(QnaRequestDto.ModifyCategoryDto requestDto) {
        this.qnaTitle = requestDto.getQnaTitle();
        this.contents = requestDto.getContents();

    }
}
