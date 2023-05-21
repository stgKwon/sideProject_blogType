package com.blogtype.sideproject.repository.qna.Impl;

import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.model.qna.Qna;
import com.blogtype.sideproject.repository.qna.QnaCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class QnaCustomRepositoryImpl implements QnaCustomRepository {

    private final EntityManager entityManager;

    @Override
    public Optional<List<Qna>> findAllQnaList() {
        return Optional.ofNullable(entityManager.createQuery("SELECT q FROM Qna AS q", Qna.class).getResultList());
    }

    @Override
    public Optional<Qna> findQna(Long userId, Long qnaId) {
        return Optional.ofNullable(entityManager.createQuery("SELECT q FROM Qna AS q WHERE q.Id =:qnaId AND q.userId =:userId", Qna.class)
                .setParameter("qnaId",qnaId)
                .setParameter("userId", userId)
                .getSingleResult());
    }

    @Override
    public Optional<List<Qna>> findLatestQnaList(Long userId) {
        return Optional.ofNullable(entityManager
                .createQuery("SELECT q FROM Qna AS q WHERE q.userId =:userId ORDER BY q.Id DESC ",Qna.class)
                .setParameter("userId",userId)
                .setMaxResults(4)
                .getResultList());
    }
}
