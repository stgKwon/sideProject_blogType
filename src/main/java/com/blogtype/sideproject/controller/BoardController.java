package com.blogtype.sideproject.controller;


import com.blogtype.sideproject.dto.board.BoardDTO;
import com.blogtype.sideproject.dto.util.ResponseDTO;
import com.blogtype.sideproject.service.board.BoardService;
import com.blogtype.sideproject.util.convert.ConvertUtil;
import com.blogtype.sideproject.util.response.ResponseUtils;
import com.blogtype.sideproject.util.security.UserDetailsImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog-board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    @ApiOperation(value = "블로그 전체 목록 조회" , notes = "전체 블로그 내용을 조회한다.")
    public ResponseEntity<ResponseDTO> findAllBoardListByUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        log.info("[findListByAll] checkUserId:" + userDetails.getUser().getId());
        List<BoardDTO.ResponseDto> resultList = boardService.findAllBoardListByUserId(ConvertUtil.findUserId(userDetails));
        return ResponseUtils.ok(resultList);
    }

}
