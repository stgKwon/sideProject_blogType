package com.blogtype.sideproject.model.qna;


import com.blogtype.sideproject.dto.qna.QnaRequestDto;
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
@Table(name = "TB_QNA")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EnableJpaAuditing // Auditing 활성화
@EntityListeners(AuditingEntityListener.class) // Auditing 리스너 등록
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

    @Column
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;


    public static Qna createQna(Long userId , QnaRequestDto.RequestQna requestDto){
        return Qna.builder()
                .qnaTitle(requestDto.getQnaTitle())
                .contents(requestDto.getContents())
                .userId(userId)
                .build();
    }

    public void updateQna(QnaRequestDto.ModifyQna requestDto) {
        this.qnaTitle = requestDto.getQnaTitle();
        this.contents = requestDto.getContents();

    }
}
