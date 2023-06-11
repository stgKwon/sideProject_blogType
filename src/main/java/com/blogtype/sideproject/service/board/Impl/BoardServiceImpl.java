package com.blogtype.sideproject.service.board.Impl;

import com.blogtype.sideproject.dto.board.BoardRequestDto;
import com.blogtype.sideproject.dto.board.BoardResponseDto;
import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.model.category.Category;
import com.blogtype.sideproject.repository.board.BoardRepository;
import com.blogtype.sideproject.repository.category.CategoryRepository;
import com.blogtype.sideproject.service.board.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    private static final ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();

    @Override
    @Transactional(readOnly = true)
    public List<BoardResponseDto.ResponseBoard> findAllBoardList() throws Exception {
        List<BoardResponseDto.ResponseBoard> resultList = new ArrayList<>();
        try {
            // FIXME :: NULL 체크 필요 !
            Optional<List<Board>> optionalBoardList = boardRepository.findAllBoardList();
            if (optionalBoardList.isPresent()){
                List<Board> findAllBoardList = optionalBoardList.get();
                resultList = new BoardResponseDto.ResponseBoard().boardConvertToDtoList(findAllBoardList);

            }

        }catch (Exception e) {
            log.error("[BoardService] findAllBoardList :: " , e);
            throw new Exception();
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public BoardResponseDto.ResponseBoard findBoard(Long userId, Long boardId) throws Exception {
       BoardResponseDto.ResponseBoard result = new BoardResponseDto.ResponseBoard();
        try {
            Optional<Board> optionalBoard = boardRepository.findBoard(userId,boardId);
            if (optionalBoard.isPresent()) {
                Board findBoard = optionalBoard.get();
                result = new BoardResponseDto.ResponseBoard().boardConvertToDto(findBoard);
            }
        }catch (Exception e){
            log.error("[BoardService] findBoard :: " , e);
            throw new Exception();
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public  List<BoardResponseDto.ResponseBoard> findLatestBoardList(Long userId) throws Exception {
        List<BoardResponseDto.ResponseBoard> resultList = new ArrayList<>();
        try {
            Optional<List<Board>> optionalLatestBoardList = boardRepository.findLatestBoardList(userId);
            if (optionalLatestBoardList.isPresent()){
                List<Board> findAllBoardList = optionalLatestBoardList.get();
                resultList = new BoardResponseDto.ResponseBoard().boardConvertToDtoList(findAllBoardList);

            }

        }catch (Exception e){
            log.error("[BoardService] findLatestBoardList :: " , e);
            throw new Exception();
        }
        return resultList;
    }

    @Override
    @Transactional
    public void createBoard(Long userId, BoardRequestDto.RequestBoard requestDto) throws Exception {
        try {

            Board board = Board.createBoard(userId, requestDto); // board 객체 생성
            Optional<Category> optionalCategory = categoryRepository.findCategory(userId,requestDto.getCategoryId());
            if (optionalCategory.isPresent()){ // 카테고리가 없을 경우에 대한 exception 추가 필요
                Category findCategory = optionalCategory.get();
                board.setCategory(findCategory);
                boardRepository.save(board);
            }


        }catch (Exception e){
            log.error("[BoardService] createBoard :: " , e);
            throw new Exception();

        }
    }

    @Override
    @Transactional
    public void modifyBoard(Long userId  , Long boardId , BoardRequestDto.ModifyBoard requestDto) throws Exception {

        try{
            Optional<Board> optionalBoard = boardRepository.findBoard(userId, boardId);
            if (optionalBoard.isPresent()){
                Board board = optionalBoard.get();
                board.updateBoard(requestDto);
            }

        }catch (Exception e){
            log.error("[BoardService] modifyBoard :: " , e);
            throw new Exception();
        }

    }

    @Override
    @Transactional
    public void deleteBoard(Long userId , Long boardId ) throws Exception {

        try{
            Optional<Board> optionalBoard = boardRepository.findBoard(userId, boardId);
            if (optionalBoard.isPresent()){
                Board board = optionalBoard.get();
                boardRepository.delete(board);
            }

        }catch (Exception e){
            log.error("[BoardService] deleteBoard :: " , e);
            throw new Exception();
        }

    }
}
