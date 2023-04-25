package com.blogtype.sideproject.util.response;

import com.blogtype.sideproject.dto.common.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    public static ResponseEntity<ResponseDto> ok(Object data) {
        ResponseDto responseDTO = new ResponseDto();
        responseDTO.setMessage("Success");
        responseDTO.setData(data);
        responseDTO.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok().body(responseDTO);
    }

    public static ResponseEntity<ResponseDto> error(String message, HttpStatus status) {
        ResponseDto responseDTO = new ResponseDto();
        responseDTO.setMessage(message);
        responseDTO.setStatus(status.value());

        return ResponseEntity.status(status).body(responseDTO);
    }
}
