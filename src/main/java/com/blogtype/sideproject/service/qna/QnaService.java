package com.blogtype.sideproject.service.qna;

import com.blogtype.sideproject.dto.qna.QnaRequestDto;
import com.blogtype.sideproject.dto.qna.QnaResponseDto;

import java.util.List;

public interface QnaService {
    List<QnaResponseDto.ResponseDto> findAllQnaList(Long userId) throws Exception;

    QnaResponseDto.ResponseDto findQna(Long userId, Long qnaId) throws Exception;

    List<QnaResponseDto.ResponseDto> findLatestQnaList(Long userId) throws Exception;

    void createQna(Long userId, QnaRequestDto.RequestDto requestDto) throws Exception;

    void modifyQna(Long userId,Long qnaId, QnaRequestDto.ModifyCategoryDto requestDto) throws Exception;

    void deleteQna(Long userId, Long qnaId) throws Exception;
}
