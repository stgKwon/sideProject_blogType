package com.blogtype.sideproject.controller;


import com.blogtype.sideproject.dto.util.ResponseDTO;
import com.blogtype.sideproject.util.response.ResponseUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    //카카오 로그인 API 테스트
    @PostMapping("/kakao/login")
    @ApiOperation(value = "카카오 로그인" , notes = "카카오톡 로그인 테스트 , 발급받은 인가코드를 파라미터로 전달받는다.")
    @ApiParam(value = "code" , example = "발급받은 인가코드" , required = true)
    public ResponseEntity<ResponseDTO> kakaoLogin(@RequestParam(name = "code") String code) throws Exception {
        log.info("[kakaoLogin] checkParam:" + code );

        return ResponseUtils.ok(null);
    }


}
