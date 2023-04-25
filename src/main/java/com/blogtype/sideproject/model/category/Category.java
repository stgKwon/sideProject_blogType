package com.blogtype.sideproject.model.category;

import com.blogtype.sideproject.dto.Category.CategoryDto;
import com.blogtype.sideproject.model.board.Board;
import lombok.*;
import org.springframework.boot.context.properties.bind.Name;

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
    private Long userId;

    @Column
    private String categoryName;

    @OneToMany(mappedBy = "category", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();

    public static Category createCategory(Long userId , CategoryDto.RequestDto requestDto){
        return Category.builder()
                .userId(userId)
                .categoryName(requestDto.getCategoryName())
                .build();
    }

    public void updateBoardList(Board board){
        this.boardList.add(board);
    }



}
