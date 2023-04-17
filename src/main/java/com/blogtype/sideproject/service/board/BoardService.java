package com.blogtype.sideproject.service.board;

import com.blogtype.sideproject.dto.board.BoardDTO;

import java.util.List;

public interface BoardService {

    /**
     * DESC :: 블로그 전체 목록 조회
     */
    List<BoardDTO.ResponseDto> findAllBoardListByUserId(Long userId) throws Exception;
}
