package com.blogtype.sideproject.repository.board.Impl;


import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.repository.board.BoardCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;


@Repository
@Transactional
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final EntityManager entityManager;

    /* FIXME :: Optional 처리 필요 */
    @Override
    public List<Board> findAllBoardList() {
        return entityManager.createQuery("SELECT b FROM Board AS b", Board.class).getResultList();
    }

    @Override
    public Board findBoard(Long userId, Long boardId) {
        return entityManager.createQuery("SELECT b FROM Board AS b WHERE b.Id =:boardId AND b.userId =:userId",Board.class).getSingleResult();
    }
}
