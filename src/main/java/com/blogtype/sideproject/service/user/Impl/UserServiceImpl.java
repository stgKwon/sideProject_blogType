package com.blogtype.sideproject.service.user.Impl;

import com.blogtype.sideproject.dto.user.UserRequestDto;
import com.blogtype.sideproject.dto.user.UserResponseDto;
import com.blogtype.sideproject.model.user.User;
import com.blogtype.sideproject.repository.user.UserRepository;
import com.blogtype.sideproject.service.user.UserService;
import com.blogtype.sideproject.util.aws.S3Uploader;
import com.blogtype.sideproject.util.constants.StringConstant;
import com.blogtype.sideproject.util.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper;
    private final S3Uploader s3Uploader;

    @Value("${client_id}")
    private String client_id;

    @Value("${redirect_uri}")
    private String redirect_uri;

    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public UserResponseDto.TokenInfo kakaoLogin(String code) throws Exception {
        UserResponseDto.TokenInfo result = new UserResponseDto.TokenInfo();
        try {
            /*
                FIXME :: SNS 로그인 토큰 처리 방식에 대하여 다시 생각이 필요하다.
             */
            // '인가 코드' 로 '엑세스 토큰' 요청.
            String accessToken = getAccessToken(code);
            // 유저정보 호출
            UserResponseDto.KakaoUserInfo userInfo = getKakaoUserInfo(accessToken);
            // DB 에 중복된 KakaoId 가 있는지 확인
            Optional<User> findUserByKakaoId = userRepository.findAllByKakaoId(userInfo.getKakaoId());
            // 중복 id 값 존재 확인
            if (!findUserByKakaoId.isPresent()) {
                User user = User.createUser(userInfo);
                userRepository.save(user);
            }
            // FIXME :: 저장된 User 정보를 다시 찾아오는 것이 맞을까? -> 다른 방안 생각해보기.
            Optional<User> findUserOptional = userRepository.findAllByKakaoId(userInfo.getKakaoId());
            findUserOptional.ifPresent(user -> result.setAccessToken(jwtTokenProvider.createToken(user).getAccessToken()));

        }catch(Exception e){
            log.error("[UserService] kakaoLogin :: " , e);
        }
        return result;
    }

    @Override
    public UserResponseDto.UserInfo findUserInfo(Long userId) throws Exception {

        UserResponseDto.UserInfo result = new UserResponseDto.UserInfo();
        try{
            Optional<User> findUserById = userRepository.findUser(userId);
            if (findUserById.isPresent()){
                // FIXME , 매핑 필드값 불일치 시 사이드 이펙트 확인 ,
                result = modelMapper.map(findUserById, UserResponseDto.UserInfo.class);
            }

        }catch (Exception e){
            log.error("[UserService] findUserInfo :: " , e);
        }
        return result;
    }

    @Override
    public void modifyUserInfo(Long userId , UserRequestDto.ModifyUser requestDto, MultipartFile imgFile) throws Exception {
        try{
            Optional<User> findUserById = userRepository.findUser(userId);
            //FIXME :: 수정 시 , 기존 원본 이미지 삭제 처리 필요
            String uploadImgUrl = s3Uploader.upload(imgFile, StringConstant.USER);
            requestDto.setUpdateImgUrl(uploadImgUrl);
            findUserById.ifPresent(user -> user.updateUser(requestDto));

        }catch (Exception e){
            log.error("[UserService] modifyUserInfo :: " , e);
        }
    }

    // 카카오 접근 토큰 발급 요청
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
    private UserResponseDto.KakaoUserInfo getKakaoUserInfo(String accessToken) throws Exception {

        UserResponseDto.KakaoUserInfo userInfo = new UserResponseDto.KakaoUserInfo();

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
            JsonNode properties = jsonNode.get("properties");
            JsonNode kakao_account = jsonNode.get("kakao_account");
            log.debug("### checkKakaoUserInfo ::: {} , {} , {} ", jsonNode,properties,kakao_account);

            Long id = jsonNode.get("id").asLong();
            String userName = "";
            String profileImg = "";
            String email = "";

            if (properties != null && properties.isObject()){
                userName = properties.get("nickName").isNull() ? "defaultName" : properties.get("nickname").asText();
                profileImg = properties.get("profile_image").isNull() ? "defaultImgUrl" : properties.get("profile_image").asText();
            }

            if (kakao_account != null && kakao_account.isObject()){
                email = kakao_account.get("email").isNull() ? "defaultEmail" : kakao_account.get("email").asText();
            }

            userInfo.setUserInfo(id,userName,profileImg,email);
        }catch (Exception e){
            log.error("[getKakaoUserInfo] :: ", e);
        }
        return userInfo;
    }






}
