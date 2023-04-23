package com.blogtype.sideproject.controller;


import com.blogtype.sideproject.dto.board.BoardDTO;
import com.blogtype.sideproject.dto.util.ResponseDTO;
import com.blogtype.sideproject.service.board.BoardService;
import com.blogtype.sideproject.util.convert.ConvertUtil;
import com.blogtype.sideproject.util.response.ResponseUtils;
import com.blogtype.sideproject.util.security.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
    private static final ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();

    /** FIXME :: userDetails -> AOP 처리 수정 필요 **/

    @GetMapping("/list")
    @ApiOperation(value = "블로그 전체 목록 조회" , notes = "전체 블로그 내용을 조회한다.")
    public ResponseEntity<ResponseDTO> findAllBoardList() throws Exception {
        List<BoardDTO.ResponseDto> resultList = boardService.findAllBoardList();
        return ResponseUtils.ok(resultList);
    }

    @GetMapping("/detail/{boardId}")
    @ApiOperation(value = "블로그 게시글 조회" , notes = "선택 블로그 내용을 조회한다.")
    public ResponseEntity<ResponseDTO> findBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "boardId") Long boardId) throws Exception {
        BoardDTO.ResponseDto result = boardService.findBoard(ConvertUtil.findUserId(userDetails),boardId);
        return ResponseUtils.ok(result);
    }

    @PostMapping("/create")
    @ApiOperation(value = "블로그 게시글 생성" , notes = "블로그 게시글을 생성한다.")
    public ResponseEntity<ResponseDTO> createBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, BoardDTO.RequestDto requestDto) throws Exception {
        log.info("[BoardController] createBoard_checkUserId: " + userDetails.getUser().getId());
        log.info("[BoardController] createBoard_requestDto: " + writer.writeValueAsString(requestDto));
        int result = boardService.createBoard(ConvertUtil.findUserId(userDetails),requestDto);
        return ResponseUtils.ok(result);
    }

    @PatchMapping("/modify/{boardId}")
    @ApiOperation(value = "블로그 게시글 수정" , notes = "선택 블로그 내용을 수정한다.")
    public ResponseEntity<ResponseDTO> modifyBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "boardId") Long boardId) throws Exception {
        log.info("[BoardController] modifyBoard_checkUserId: " + userDetails.getUser().getId());
        return ResponseUtils.ok(null);
    }

    @DeleteMapping("/delete/{boardId}")
    @ApiOperation(value = "블로그 게시글 삭제" , notes = "선택 블로그 내용을 삭제한다.")
    public ResponseEntity<ResponseDTO> deleteBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "boardId") Long boardId) throws Exception {
        log.info("[BoardController] deleteBoard_checkUserId: " + userDetails.getUser().getId());
        return ResponseUtils.ok(null);
    }





}
