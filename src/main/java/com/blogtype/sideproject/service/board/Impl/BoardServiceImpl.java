package com.blogtype.sideproject.service.board.Impl;

import com.blogtype.sideproject.dto.board.BoardDto;
import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.model.category.Category;
import com.blogtype.sideproject.repository.board.BoardRepository;
import com.blogtype.sideproject.repository.category.CategoryRepository;
import com.blogtype.sideproject.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {


    private final BoardRepository boardRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BoardDto.ResponseDto> findAllBoardList() throws Exception {
        List<BoardDto.ResponseDto> resultList = new ArrayList<>();
        try {
            // FIXME :: NULL 체크 필요 !
            List<Board> findAllBoardList = boardRepository.findAllBoardList();
            resultList = findAllBoardList.stream()
                            .map(entity -> modelMapper.map(entity, BoardDto.ResponseDto.class))
                            .collect(Collectors.toList());

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
            // FIXME :: NULL 체크 필요 ! , categoryId 필요성에 대한 테스트 필요
            Board board = boardRepository.findBoard(userId,boardId);
            result = modelMapper.map(board, BoardDto.ResponseDto.class);

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

            Optional<Category> findCategoryById = categoryRepository.findCategory(userId,requestDto.getCategoryId());
            findCategoryById.ifPresent(category -> category.updateBoardList(board));

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
