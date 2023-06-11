package com.blogtype.sideproject.service.board;

import com.blogtype.sideproject.dto.board.BoardRequestDto;
import com.blogtype.sideproject.dto.board.BoardResponseDto;

import java.util.List;

public interface BoardService {

    /**
     * DESC :: 블로그 전체 목록 조회
     */
    List<BoardResponseDto.ResponseBoard> findAllBoardList() throws Exception;

    /**
     * DESC :: 블로그 전체 목록 조회
     */
    BoardResponseDto.ResponseBoard findBoard(Long userId, Long boardId) throws Exception;

    /**
     * DESC :: 블로그 최신글 조회
     */
    List<BoardResponseDto.ResponseBoard> findLatestBoardList(Long userId) throws Exception;

    /**
     * DESC :: 블로그 게시글 생성
     */
    void createBoard(Long userId, BoardRequestDto.RequestBoard requestDto) throws Exception;

    /**
     * DESC :: 블로그 게시글 수정
     */
    void modifyBoard(Long userId , Long boardId , BoardRequestDto.ModifyBoard requestDto) throws Exception;

    /**
     * DESC :: 블로그 게시글 삭제
     */
    void deleteBoard(Long userId, Long BoardId ) throws Exception;


}
