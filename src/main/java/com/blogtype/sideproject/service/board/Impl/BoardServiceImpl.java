package com.blogtype.sideproject.service.board.Impl;

import com.blogtype.sideproject.dto.board.BoardDto;
import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.model.category.Category;
import com.blogtype.sideproject.repository.board.BoardRepository;
import com.blogtype.sideproject.repository.category.CategoryRepository;
import com.blogtype.sideproject.service.board.BoardService;
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
@Transactional
public class BoardServiceImpl implements BoardService {


    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BoardDto.ResponseDto> findAllBoardList() throws Exception {
        List<BoardDto.ResponseDto> resultList = new ArrayList<>();
        try {
            // FIXME :: NULL 체크 필요 !
            Optional<List<Board>> optionalBoardList = boardRepository.findAllBoardList();
            if (optionalBoardList.isPresent()){
                List<Board> findAllBoardList = optionalBoardList.get();
                resultList = new BoardDto.ResponseDto().boardConvertToDtoList(findAllBoardList);


            }


        }catch (Exception e) {
            log.error("[BoardService] findAllBoardList :: " , e);
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public BoardDto.ResponseDto findBoard(Long userId, Long boardId) throws Exception {
       BoardDto.ResponseDto result = new BoardDto.ResponseDto();
        try {
            Optional<Board> optionalBoard = boardRepository.findBoard(userId,boardId);
            if (optionalBoard.isPresent()) {
                Board findBoard = optionalBoard.get();
                result = new BoardDto.ResponseDto().boardConvertToDto(findBoard);
            }
        }catch (Exception e){
            log.error("[BoardService] findBoard :: " , e);
        }
        return result;
    }

    @Override
    public void createBoard(Long userId, BoardDto.RequestDto requestDto) throws Exception {
        try {

            Board board = Board.createBoard(userId, requestDto);
            boardRepository.save(board);
            // FIXME :: 좀 더 생각해봐야할 듯 싶다...
            Optional<Category> optionalCategory = categoryRepository.findCategory(userId,requestDto.getCategoryId());
            if (optionalCategory.isPresent()){
                Category findCategory = optionalCategory.get();
                findCategory.updateBoardList(board);
            }

        }catch (Exception e){
            log.error("[BoardService] createBoard :: " , e);
        }
    }

    @Override
    public void modifyBoard() throws Exception {

    }

    @Override
    public void deleteBoard() throws Exception {

    }
}
