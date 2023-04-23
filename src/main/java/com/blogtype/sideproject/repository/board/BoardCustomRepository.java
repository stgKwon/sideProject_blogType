package com.blogtype.sideproject.repository.board;

import com.blogtype.sideproject.model.board.Board;

import java.util.List;

public interface BoardCustomRepository {

    /*
        DESC :: 전체 게시글 조회
     */
    List<Board> findAllBoardList();

    /*
        DESC :: 단일 게시글 조회
     */
    Board findBoard(Long userId, Long boardId);
}

