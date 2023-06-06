package com.blogtype.sideproject.controller;


import com.blogtype.sideproject.dto.board.BoardRequestDto;
import com.blogtype.sideproject.dto.board.BoardResponseDto;
import com.blogtype.sideproject.dto.common.ResponseDto;
import com.blogtype.sideproject.service.board.BoardService;
import com.blogtype.sideproject.util.convert.ConvertUtil;
import com.blogtype.sideproject.util.response.ResponseUtils;
import com.blogtype.sideproject.util.security.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"BOARD API"})
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
    public ResponseEntity<ResponseDto> findAllBoardList(@AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        List<BoardResponseDto.ResponseDto> resultList = boardService.findAllBoardList();
        return ResponseUtils.ok(resultList);
    }

    @GetMapping("/detail/{boardId}")
    @ApiOperation(value = "블로그 게시글 조회" , notes = "선택 블로그 내용을 조회한다.")
    public ResponseEntity<ResponseDto> findBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "boardId") Long boardId) throws Exception {
        BoardResponseDto.ResponseDto result = boardService.findBoard(ConvertUtil.findUserId(userDetails),boardId);
        return ResponseUtils.ok(result);
    }

    @GetMapping("/latest")
    @ApiOperation(value = "블로그 게시글 최신순 조회" , notes = "해당 유저가 작성한 최신순 블로그 게시글 4개를 조회한다.")
    public ResponseEntity<ResponseDto> findLatestBoardList(@AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        List<BoardResponseDto.ResponseDto> resultList = boardService.findLatestBoardList(ConvertUtil.findUserId(userDetails));
        return ResponseUtils.ok(resultList);
    }

    @PostMapping("/create")
    @ApiOperation(value = "블로그 게시글 생성" , notes = "블로그 게시글을 생성한다.")
    public ResponseEntity<ResponseDto> createBoard(@AuthenticationPrincipal UserDetailsImpl userDetails,  @RequestBody BoardRequestDto.RequestDto boardRequestDto) throws Exception {
        log.info("[BoardController] createBoard_checkUserId: " + userDetails.getUser().getId());
        boardService.createBoard(ConvertUtil.findUserId(userDetails),boardRequestDto);
        return ResponseUtils.ok(null);
    }

    @PatchMapping("/modify/{boardId}")
    @ApiOperation(value = "블로그 게시글 수정" , notes = "선택 블로그 내용을 수정한다.")
    public ResponseEntity<ResponseDto> modifyBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "boardId") Long boardId, @RequestBody BoardRequestDto.ModifyBoardDto boardModRequestDto) throws Exception {
        log.info("[BoardController] modifyBoard_checkUserId: " + userDetails.getUser().getId());
        boardService.modifyBoard(ConvertUtil.findUserId(userDetails), boardId , boardModRequestDto);
        return ResponseUtils.ok(null);
    }

    @DeleteMapping("/delete/{boardId}")
    @ApiOperation(value = "블로그 게시글 삭제" , notes = "선택 블로그 내용을 삭제한다." )
    public ResponseEntity<ResponseDto> deleteBoard(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "boardId") Long boardId) throws Exception {
        log.info("[BoardController] deleteBoard_checkUserId: " + userDetails.getUser().getId());
        boardService.deleteBoard(ConvertUtil.findUserId(userDetails), boardId);
        return ResponseUtils.ok(null);
    }





}
