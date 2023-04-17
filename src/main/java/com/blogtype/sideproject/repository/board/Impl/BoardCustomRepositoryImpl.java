package com.blogtype.sideproject.repository.board.Impl;


import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.repository.board.BoardCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final EntityManager entityManager;

    @Override
    @Transactional
    public List<Board> findAllBoardListByUserId(Long userId) {
        return entityManager.createQuery("SELECT b FROM Board AS b WHERE b.userId = :userId ", Board.class).getResultList();
    }
}
