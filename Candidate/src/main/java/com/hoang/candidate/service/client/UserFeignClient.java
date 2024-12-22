package com.hoang.candidate.service.client;

import com.hoang.candidate.dto.ResponseDto;
import com.hoang.candidate.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient("user")
public interface UserFeignClient {

    @GetMapping(value = "api/user", consumes = "application/json")
    ResponseEntity<UserDto> getUser(@RequestParam String id);

    @PostMapping(value = "api/user/create", produces = "application/json")
    ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto);

    @PutMapping(value = "api/user/update", produces = "application/json")
    ResponseEntity<ResponseDto> updateUser(@RequestBody UserDto userDto);

    @DeleteMapping(value = "api/user/delete", produces = "application/json")
    ResponseEntity<ResponseDto> deleteUser(@RequestParam String id);
}
