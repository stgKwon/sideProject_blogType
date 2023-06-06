package com.blogtype.sideproject.controller;


import com.blogtype.sideproject.dto.common.ResponseDto;
import com.blogtype.sideproject.dto.qna.QnaRequestDto;
import com.blogtype.sideproject.dto.qna.QnaResponseDto;
import com.blogtype.sideproject.service.qna.QnaService;
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

@Api(tags = {"QNA API"})
@RestController
@RequestMapping("/qna")
@RequiredArgsConstructor
@Slf4j
public class QnaController {

    private final QnaService qnaService;
    private static final ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();

    @GetMapping("/list")
    @ApiOperation(value = "QnA 전체 목록 조회" , notes = "전체 블로그 내용을 조회한다.")
    public ResponseEntity<ResponseDto> findAllQnaList(@AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        log.info("[QnaController] findAllQnaList_checkUserId: " + userDetails.getUser().getId());
        List<QnaResponseDto.ResponseDto> resultList = qnaService.findAllQnaList(ConvertUtil.findUserId(userDetails));
        return ResponseUtils.ok(resultList);
    }

    @GetMapping("/detail/{qnaId}")
    @ApiOperation(value = "QnA 조회" , notes = "선택 QnA 내용을 조회한다.")
    public ResponseEntity<ResponseDto> findQna(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable(name = "qnaId") Long qnaId) throws Exception {
        log.info("[QnaController] findQna_checkUserId: " + userDetails.getUser().getId());
        QnaResponseDto.ResponseDto result = qnaService.findQna(ConvertUtil.findUserId(userDetails),qnaId);
        return ResponseUtils.ok(result);
    }

    @GetMapping("/latest")
    @ApiOperation(value = "QnA 글 최신순 조회" , notes = "해당 유저의 QnA 를 최신순 4개를 조회한다.")
    public ResponseEntity<ResponseDto> findLatestQnaList(@AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        log.info("[QnaController] findLatestQnaList_checkUserId: " + userDetails.getUser().getId());
        List<QnaResponseDto.ResponseDto> resultList = qnaService.findLatestQnaList(ConvertUtil.findUserId(userDetails));
        return ResponseUtils.ok(resultList);
    }

    @PostMapping("/create")
    @ApiOperation(value = "QnA 글 생성" , notes = "QnA 글을 생성한다.")
    public ResponseEntity<ResponseDto> createQna(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody QnaRequestDto.RequestDto qnaRequestDto) throws Exception {
        log.info("[QnaController] createQna_checkUserId: " + userDetails.getUser().getId());
        qnaService.createQna(ConvertUtil.findUserId(userDetails),qnaRequestDto);
        return ResponseUtils.ok(null);
    }

    @PatchMapping("/modify/{qnaId}")
    @ApiOperation(value = "QnA 글 수정" , notes = "해당 유저의 QnA 글을 수정한다.")
    public ResponseEntity<ResponseDto> modifyQna(@AuthenticationPrincipal UserDetailsImpl userDetails,  @PathVariable(name = "qnaId") Long qnaId ,@RequestBody QnaRequestDto.ModifyCategoryDto qnaModRequestDto) throws Exception {
        log.info("[QnaController] modifyQna_checkUserId: " + userDetails.getUser().getId());
        qnaService.modifyQna(ConvertUtil.findUserId(userDetails) , qnaId, qnaModRequestDto);
        return ResponseUtils.ok(null);
    }

    @DeleteMapping("/delete/{qnaId}")
    @ApiOperation(value = "QnA 글 삭제" , notes = "QnA 글을 삭제한다.")
    public ResponseEntity<ResponseDto> deleteQna(@AuthenticationPrincipal UserDetailsImpl userDetails,  @PathVariable(name = "qnaId") Long qnaId) throws Exception {
        log.info("[QnaController] deleteQna_checkUserId: " + userDetails.getUser().getId());
        qnaService.deleteQna(ConvertUtil.findUserId(userDetails) , qnaId);
        return ResponseUtils.ok(null);
    }
}
