package com.blogtype.sideproject;


import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.repository.board.BoardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class TestServiceImpl {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void test(){
        System.out.println("test");

    }

}
