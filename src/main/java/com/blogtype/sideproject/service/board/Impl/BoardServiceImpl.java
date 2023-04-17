package com.blogtype.sideproject.service.board.Impl;

import com.blogtype.sideproject.dto.board.BoardDTO;
import com.blogtype.sideproject.dto.util.ResponseDTO;
import com.blogtype.sideproject.model.board.Board;
import com.blogtype.sideproject.repository.board.BoardRepository;
import com.blogtype.sideproject.service.board.BoardService;
import com.blogtype.sideproject.util.response.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {


    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public List<BoardDTO.ResponseDto> findAllBoardListByUserId(Long userId) throws Exception {
        List<BoardDTO.ResponseDto> resultList = new ArrayList<>();
        try {
            List<Board> findAllBoardListByUserId = boardRepository.findAllBoardListByUserId(userId);
            resultList = findAllBoardListByUserId.stream()
                            .map(entity -> modelMapper.map(entity, BoardDTO.ResponseDto.class))
                            .collect(Collectors.toList());

        }catch (Exception e) {
            log.error("[BoardService] findAllBoardListByUserId :: " , e);
        }

        return resultList;
    }
}
