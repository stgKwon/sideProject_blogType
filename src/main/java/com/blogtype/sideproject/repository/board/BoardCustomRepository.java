package com.blogtype.sideproject.repository.board;

import com.blogtype.sideproject.model.board.Board;

import java.util.List;

public interface BoardCustomRepository {

    List<Board> findAllBoardList();
}
