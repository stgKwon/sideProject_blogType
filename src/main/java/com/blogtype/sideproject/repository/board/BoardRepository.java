package com.blogtype.sideproject.repository.board;

import com.blogtype.sideproject.model.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardCustumRepository {
}
