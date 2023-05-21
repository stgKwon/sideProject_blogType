package com.blogtype.sideproject.repository.qna;

import com.blogtype.sideproject.model.qna.Qna;

import java.util.List;
import java.util.Optional;

public interface QnaCustomRepository {

    /*
        DESC :: 전체 Qna 조회
    */
    Optional<List<Qna>> findAllQnaList();

    /*
        DESC :: 단일 Qna 조회
     */
    Optional<Qna> findQna(Long userId, Long qnaId);

    /*
        DESC :: Qna 최신순 조회
    */
    Optional<List<Qna>> findLatestQnaList(Long userId);


}
