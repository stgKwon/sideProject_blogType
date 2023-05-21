package com.blogtype.sideproject.repository.board.Impl;


import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.repository.board.BoardCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository
@Transactional
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final EntityManager entityManager;

    /* FIXME :: Optional 처리 필요 */
    @Override
    public Optional<List<Board>> findAllBoardList() {
        return Optional.ofNullable(entityManager.createQuery("SELECT b FROM Board AS b", Board.class).getResultList());
    }

    @Override
    public Optional<Board> findBoard(Long userId, Long boardId) {
        return Optional.ofNullable(entityManager.createQuery("SELECT b FROM Board AS b WHERE b.Id =:boardId AND b.userId =:userId", Board.class)
                .setParameter("boardId", boardId)
                .setParameter("userId", userId)
                .getSingleResult());
    }

    @Override
    public Optional<List<Board>> findLatestBoardList(Long userId) {
        return Optional.ofNullable(entityManager
                .createQuery("SELECT b FROM Board AS b WHERE b.userId =:userId ORDER BY b.Id DESC ",Board.class)
                .setParameter("userId",userId)
                .setMaxResults(4)
                .getResultList());
    }
}
