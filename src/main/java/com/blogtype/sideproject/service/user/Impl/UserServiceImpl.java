package com.blogtype.sideproject.service.user.Impl;

import com.blogtype.sideproject.dto.user.UserDTO;
import com.blogtype.sideproject.dto.util.ResponseDTO;
import com.blogtype.sideproject.model.user.User;
import com.blogtype.sideproject.repository.user.UserRepository;
import com.blogtype.sideproject.service.user.UserService;
import com.blogtype.sideproject.util.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${client_id}")
    private String client_id;

    @Value("${redirect_uri}")
    private String redirect_uri;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional
    public UserDTO.ResponseDto kakaoLogin(String code) throws Exception {
        UserDTO.ResponseDto result = new UserDTO.ResponseDto();
        try {
            /*
                FIXME :: SNS 로그인 토큰 처리 방식에 대하여 다시 생각이 필요하다.
             */

            // '인가 코드' 로 '엑세스 토큰' 요청.
            String accessToken = getAccessToken(code);
            // 유저정보 호출
            UserDTO.KakaoUserInfo userInfo = getKakaoUserInfo(accessToken);
            // DB 에 중복된 KakaoId 가 있는지 확인
            Optional<User> findUserByKakaoId = userRepository.findAllByKakaoId(userInfo.getKakaoId());
            // 중복 id 값 존재 확인
            if (!findUserByKakaoId.isPresent()) {
                User user = User.createUser(userInfo);
                userRepository.save(user);
            }

            Optional<User> findUserOptional = userRepository.findAllByKakaoId(userInfo.getKakaoId());
            findUserOptional.ifPresent(user -> result.setAccessToken(jwtTokenProvider.createToken(user).getAccessToken()));

        }catch(Exception e){
            log.error("[UserService] kakaoLogin :: " , e);
        }
        return result;
    }

    private String getAccessToken(String code) throws Exception {

        String accessToken = "";
        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HTTP Body 생성
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", client_id);
        body.add("redirect_uri", redirect_uri);
        body.add("code", code);

        // HTTP 요청 보내기
        try {
            HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
            RestTemplate rt = new RestTemplate();
            ResponseEntity<String> response = rt.exchange(
                    "https://kauth.kakao.com/oauth/token",
                    HttpMethod.POST,
                    kakaoTokenRequest,
                    String.class
            );
            // HTTP 응답 (JSON) -> 액세스 토큰 파싱
            String responseBody = response.getBody();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            accessToken = jsonNode.get("access_token").asText();

        } catch (Exception e){
            log.error("[getAccessToken] :: " , e);
        }

        return accessToken;
    }

    // 유저 정보
    private UserDTO.KakaoUserInfo getKakaoUserInfo(String accessToken) throws Exception {

        UserDTO.KakaoUserInfo userInfo = new UserDTO.KakaoUserInfo();

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        try {
            // HTTP 요청 보내기
            HttpEntity<MultiValueMap<String, String>> kakaoUserInfoRequest = new HttpEntity<>(headers);
            RestTemplate rt = new RestTemplate();
            ResponseEntity<String> response = rt.exchange(
                    "https://kapi.kakao.com/v2/user/me",
                    HttpMethod.POST,
                    kakaoUserInfoRequest,
                    String.class
            );

            String responseBody = response.getBody();
            JsonNode jsonNode = objectMapper.readTree(responseBody);

            Long id = jsonNode.get("id").asLong();
            String userName = "";
            if (jsonNode.get("properties") != null && !jsonNode.get("properties").isEmpty()){
                userName = jsonNode.get("properties").get("nickname").toString();
            }
            String email = jsonNode.get("kakao_account").get("email").asText();

            userInfo.setUserInfo(id,userName,email);
        }catch (Exception e){
            log.error("[getKakaoUserInfo] :: ", e);
        }
        return userInfo;
    }
}
