package com.hoang.employer.service.client.fallback;

import com.hoang.employer.constant.Constants;
import com.hoang.employer.dto.ResponseDto;
import com.hoang.employer.dto.UserDto;
import com.hoang.employer.service.client.UserFeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

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
