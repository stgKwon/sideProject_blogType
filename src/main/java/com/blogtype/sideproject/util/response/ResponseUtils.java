package com.blogtype.sideproject.util.response;

import com.blogtype.sideproject.dto.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {

    public static ResponseEntity<ResponseDTO> ok(Object data) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage("Success");
        responseDTO.setData(data);
        responseDTO.setStatus(HttpStatus.OK.value());

        return ResponseEntity.ok().body(responseDTO);
    }

    public static ResponseEntity<ResponseDTO> error(String message, HttpStatus status) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(message);
        responseDTO.setStatus(status.value());

        return ResponseEntity.status(status).body(responseDTO);
    }
}
