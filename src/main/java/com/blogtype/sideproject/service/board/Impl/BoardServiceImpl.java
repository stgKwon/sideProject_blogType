package com.blogtype.sideproject.service.board.Impl;

import com.blogtype.sideproject.dto.board.BoardDTO;
import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.repository.board.BoardRepository;
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
    private final ModelMapper modelMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BoardDTO.ResponseDto> findAllBoardList() throws Exception {
        List<BoardDTO.ResponseDto> resultList = new ArrayList<>();
        try {
            // FIXME :: NULL 체크 필요 !
            List<Board> findAllBoardList = boardRepository.findAllBoardList();
            resultList = findAllBoardList.stream()
                            .map(entity -> modelMapper.map(entity, BoardDTO.ResponseDto.class))
                            .collect(Collectors.toList());

        }catch (Exception e) {
            log.error("[BoardService] findAllBoardList :: " , e);
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public BoardDTO.ResponseDto findBoard(Long userId, Long boardId) throws Exception {
       BoardDTO.ResponseDto result = new BoardDTO.ResponseDto();
        try {
            // FIXME :: NULL 체크 필요 !
            Board board = boardRepository.findBoard(userId,boardId);
            result = modelMapper.map(board, BoardDTO.ResponseDto.class);

        }catch (Exception e){
            log.error("[BoardService] findBoard :: " , e);
        }
        return result;
    }

    @Override
    public int createBoard(Long userId, BoardDTO.RequestDto requestDto) throws Exception {
        int result = 0;
        try {
            /*
                FIXME :: NULL 체크 수정
             */
            Board board = Board.createBoard(userId, requestDto);
            Optional<Long> saveBoardIdOptional = Optional.ofNullable(boardRepository.save(board).getId());
            if (saveBoardIdOptional.isPresent()){
                result = saveBoardIdOptional.get().intValue();
            }

        }catch (Exception e){
            log.error("[BoardService] createBoard :: " , e);
        }
        return result;
    }

    @Override
    public int modifyBoard() throws Exception {
        return 0;
    }

    @Override
    public int deleteBoard() throws Exception {
        return 0;
    }
}
