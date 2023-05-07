package com.blogtype.sideproject.service.user.Impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceImplTest {

    static {
        System.setProperty("com.amazonaws.sdk.disableEc2Metadata", "true");
    }

    // 카카오 접근 토큰 테스트
    @Test
    void getAuthCode() {
        //Spring restTemplate
        HashMap<String, Object> result = new HashMap<>();
        ResponseEntity<Object> resultMap = new ResponseEntity<>(null,null,200);
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders header = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(header);
//            String url = "https://kauth.kakao.com/oauth/authorize?client_id=047ec2a70edbb613094683ba332ce8fa&redirect_uri=http://43.201.115.136:8080/user/kakao/login&response_type=code";

            String url = "https://kauth.kakao.com/oauth/authorize?client_id=047ec2a70edbb613094683ba332ce8fa&redirect_uri=http://localhost:8080/user/kakao/login&response_type=code";
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

            resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Object.class);

            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인


        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());

        }
        System.out.println(result);
    }

    @Test
    void kakaoLogin() {
    }

    @Test
    void findUserInfo() {
    }

    @Test
    void modifyUserInfo() {
    }
}