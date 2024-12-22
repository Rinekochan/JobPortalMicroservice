package com.hoang.candidate.service.client.fallback;

import com.hoang.candidate.constant.Constants;
import com.hoang.candidate.dto.ResponseDto;
import com.hoang.candidate.dto.UserDto;
import com.hoang.candidate.service.client.UserFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class UserFallbackFeign implements UserFeignClient {

    @Override
    public ResponseEntity<UserDto> getUser(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
    }

    @Override
    public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto){
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ResponseDto.builder()
                        .statusCode("503")
                        .statusMsg(Constants.FEIGN_CLIENT_ERROR)
                        .build()
                );
    }

    @Override
    public ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto){
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ResponseDto.builder()
                        .statusCode("503")
                        .statusMsg(Constants.FEIGN_CLIENT_ERROR)
                        .build()
                );
    }

    @Override
    public ResponseEntity<ResponseDto> deleteUser(@RequestParam String id){
        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(ResponseDto.builder()
                        .statusCode("503")
                        .statusMsg(Constants.FEIGN_CLIENT_ERROR)
                        .build()
                );
    }
}
