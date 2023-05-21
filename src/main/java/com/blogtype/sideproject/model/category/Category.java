package com.blogtype.sideproject.model.category;

import com.blogtype.sideproject.dto.category.CategoryRequestDto;
import com.blogtype.sideproject.dto.category.CategoryResponseDto;
import com.blogtype.sideproject.model.board.Board;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "TB_CATEGORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    @NotNull
    private Long userId;

    @Column
    @NotNull
    private String categoryName;

    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Board> boardList = new ArrayList<>();

    public static Category createCategory(Long userId , CategoryRequestDto.RequestDto requestDto){
        return Category.builder()
                .userId(userId)
                .categoryName(requestDto.getCategoryName())
                .build();
    }

    public void updateBoardList(Board board){
        this.boardList.add(board);
    }

    public void updateCategory(CategoryRequestDto.ModifyCategoryDto requestDto){
        this.categoryName = requestDto.getCategoryName();

    }



}
