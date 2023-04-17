package com.blogtype.sideproject.repository.board;

import com.blogtype.sideproject.model.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardCustomRepository {

}
