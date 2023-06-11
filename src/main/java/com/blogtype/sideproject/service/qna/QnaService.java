package com.blogtype.sideproject.service.qna;

import com.blogtype.sideproject.dto.qna.QnaRequestDto;
import com.blogtype.sideproject.dto.qna.QnaResponseDto;

import java.util.List;

public interface QnaService {
    List<QnaResponseDto.ResponseQna> findAllQnaList(Long userId) throws Exception;

    QnaResponseDto.ResponseQna findQna(Long userId, Long qnaId) throws Exception;

    List<QnaResponseDto.ResponseQna> findLatestQnaList(Long userId) throws Exception;

    void createQna(Long userId, QnaRequestDto.RequestQna requestDto) throws Exception;

    void modifyQna(Long userId,Long qnaId, QnaRequestDto.ModifyQna requestDto) throws Exception;

    void deleteQna(Long userId, Long qnaId) throws Exception;
}
