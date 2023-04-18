package com.blogtype.sideproject.service.board;

import com.blogtype.sideproject.dto.board.BoardDTO;

import java.util.List;

public interface BoardService {

    /**
     * DESC :: 블로그 전체 목록 조회
     */
    List<BoardDTO.ResponseDto> findAllBoardList() throws Exception;

    /**
     * DESC :: 블로그 전체 목록 조회
     */
    BoardDTO.ResponseDto findBoard() throws Exception;

    /**
     * DESC :: 블로그 게시글 생성
     */
    int createBoard(Long userId, BoardDTO.RequestDto requestDto) throws Exception;

    /**
     * DESC :: 블로그 게시글 수정
     */
    int modifyBoard() throws Exception;

    /**
     * DESC :: 블로그 게시글 삭제
     */
    int deleteBoard() throws Exception;
}
