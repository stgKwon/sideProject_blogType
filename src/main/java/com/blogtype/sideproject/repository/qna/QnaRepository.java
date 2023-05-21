package com.blogtype.sideproject.repository.qna;

import com.blogtype.sideproject.model.qna.Qna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QnaRepository extends JpaRepository<Qna,Long> , QnaCustomRepository {
}
