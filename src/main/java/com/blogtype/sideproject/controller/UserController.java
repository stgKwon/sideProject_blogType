package com.blogtype.sideproject.controller;


import com.blogtype.sideproject.dto.user.UserDTO;
import com.blogtype.sideproject.dto.common.ResponseDTO;
import com.blogtype.sideproject.service.user.UserService;
import com.blogtype.sideproject.util.response.ResponseUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    //카카오 로그인 API 테스트
    @PostMapping("/kakao/login")
    @ApiOperation(value = "카카오 로그인" , notes = "카카오톡 로그인 테스트 , 발급받은 인가코드를 파라미터로 전달받는다.")
    @ApiParam(value = "code" , example = "발급받은 인가코드" , required = true)
    public ResponseEntity<ResponseDTO> kakaoLogin(@RequestParam(name = "code") String code) throws Exception {
        log.info("[kakaoLogin] checkParam: " + code );
        UserDTO.ResponseDto result = userService.kakaoLogin(code);
        return ResponseUtils.ok(result);
    }

    //회원정보 조회
    @GetMapping("/info/{userId}")
    @ApiOperation(value = "회원 정보 조회" , notes = "선택한 회원의 정보를 조회한다.")
    public ResponseEntity<ResponseDTO> findUserInfo(@PathVariable(name = "userId") Long userId) throws Exception {
        log.info("[findUserInfo] checkParam: " + userId );
        UserDTO.ResponseUserInfo result = userService.findUserInfo(userId);
        return ResponseUtils.ok(result);
    }

}
