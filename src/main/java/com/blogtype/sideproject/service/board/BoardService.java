package com.blogtype.sideproject.service.board;

import com.blogtype.sideproject.dto.board.BoardDto;

import java.util.List;

public interface BoardService {

    /**
     * DESC :: 블로그 전체 목록 조회
     */
    List<BoardDto.ResponseDto> findAllBoardList() throws Exception;

    /**
     * DESC :: 블로그 전체 목록 조회
     */
    BoardDto.ResponseDto findBoard(Long userId, Long boardId) throws Exception;

    /**
     * DESC :: 블로그 게시글 생성
     */
    void createBoard(Long userId, BoardDto.RequestDto requestDto) throws Exception;

    /**
     * DESC :: 블로그 게시글 수정
     */
    void modifyBoard() throws Exception;

    /**
     * DESC :: 블로그 게시글 삭제
     */
    void deleteBoard() throws Exception;
}
