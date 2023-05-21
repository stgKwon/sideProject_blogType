package com.blogtype.sideproject.controller;


import com.blogtype.sideproject.dto.common.ResponseDto;
import com.blogtype.sideproject.dto.user.UserRequestDto;
import com.blogtype.sideproject.dto.user.UserResponseDto;
import com.blogtype.sideproject.service.user.UserService;
import com.blogtype.sideproject.util.convert.ConvertUtil;
import com.blogtype.sideproject.util.response.ResponseUtils;
import com.blogtype.sideproject.util.security.UserDetailsImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = {"USER API"})
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    private static final ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();


    //카카오 로그인 API 테스트
    @PostMapping("/kakao/login")
    @ApiOperation(value = "카카오 로그인" , notes = "카카오톡 로그인 테스트 , 발급받은 인가코드를 파라미터로 전달받는다.")
    @ApiParam(value = "code" , example = "발급받은 인가코드" , required = true)
    public ResponseEntity<ResponseDto> kakaoLogin(@RequestParam(name = "code") String code) throws Exception {
        log.info("[kakaoLogin] checkParam: " + code );
        UserResponseDto.TokenInfo result = userService.kakaoLogin(code);
        return ResponseUtils.ok(result);
    }

    //회원정보 조회
    @GetMapping("/info/{userId}")
    @ApiOperation(value = "회원 정보 조회" , notes = "선택한 회원의 정보를 조회한다.")
    public ResponseEntity<ResponseDto> findUserInfo(@PathVariable(name = "userId") Long userId) throws Exception {
        log.info("[findUserInfo] checkParam: " + userId );
        UserResponseDto.UserInfo result = userService.findUserInfo(userId);
        return ResponseUtils.ok(result);
    }


    //회원정보 수정
    @PatchMapping("/info/modify")
    @ApiOperation(value = "회원 정보 수정" , notes = "회원 정보를 수정한다.")
    public ResponseEntity<ResponseDto> modifyUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                                      @RequestBody UserRequestDto.ModifyUserDto requestDto,
                                                      @RequestPart MultipartFile imgFile) throws Exception {
        log.info("[modifyUserInfo] checkParam: " + writer.writeValueAsString(requestDto));
        userService.modifyUserInfo(ConvertUtil.findUserId(userDetails) , requestDto , imgFile);
        return ResponseUtils.ok(null);
    }

    //회원정보 수정
    @GetMapping("/info/write-date")
    @ApiOperation(value = "회원이 작성한 글에 대한 일자 및 작성 수" , notes = "회읜이 작성한 블로그 및 QnA 글의 일자 및 작성 수를 응답.")
    public ResponseEntity<ResponseDto> findDateByWrite(@AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        userService.findDateByWrite(ConvertUtil.findUserId(userDetails));
        return ResponseUtils.ok(null);
    }



}
