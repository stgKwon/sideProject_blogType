package com.blogtype.sideproject.repository.board;

import com.blogtype.sideproject.model.board.Board;

import java.util.List;
import java.util.Optional;

public interface BoardCustomRepository {

    /*
        DESC :: 전체 게시글 조회
     */
    Optional<List<Board>> findAllBoardList();

    /*
        DESC :: 단일 게시글 조회
     */
    Optional<Board> findBoard(Long userId, Long boardId);

    /*
      DESC :: 블로그 게시글 최신순 조회
    */
    Optional<List<Board>> findLatestBoardList(Long userId);
}

