package com.blogtype.sideproject.model.category;

import com.blogtype.sideproject.dto.category.CategoryRequestDto;
import com.blogtype.sideproject.model.board.Board;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "TB_CATEGORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EnableJpaAuditing // Auditing 활성화
@EntityListeners(AuditingEntityListener.class) // Auditing 리스너 등록
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    @NotNull
    private Long userId;

    @Column
    @NotNull
    private String categoryName;

    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    @Column
    @LastModifiedDate // 수정 시간 자동 업데이트
    private LocalDateTime modTime;

    @Column(updatable = false)
    @CreatedDate // 등록 시간 자동 업데이트
    private LocalDateTime regTime;


    public static Category createCategory(Long userId , CategoryRequestDto.RequestCategory requestDto){
        return Category.builder()
                .userId(userId)
                .categoryName(requestDto.getCategoryName())
                .build();
    }


    public void updateCategory(CategoryRequestDto.ModifyCategory requestDto){
        this.categoryName = requestDto.getCategoryName();

    }



}
