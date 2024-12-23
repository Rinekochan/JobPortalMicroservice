package com.hoang.user.controller;

import com.hoang.user.dto.ResponseDto;
import com.hoang.user.dto.UserDto;
import com.hoang.user.entity.User;
import com.hoang.user.service.UserService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User CRUD REST APIs")
@RestController
@RequestMapping(path = "/api/user", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    @Operation(summary = "GET USER BY ID")
    @GetMapping
    @Retry(name = "getUser",fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<UserDto> getUser(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @Operation(summary = "GET ALL USERS")
    @GetMapping("/all")
    @Retry(name = "getAllUsers",fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    /**
     * Create user response entity.
     *
     * @param userDto the user dto
     * @return the response entity
     */
    @Operation(summary = "CREATE USER")
    @PostMapping("/create")
    @Retry(name = "createUser",fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UserDto userDto) {
        User user = userService.createUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseDto.builder()
                        .statusCode("201")
                        .statusMsg("User created successfully with id " + user.getId())
                        .build()
                );
    }

    /**
     * Update user response entity.
     *
     * @param userDto the user dto
     * @return the response entity
     */
    @Operation(summary = "UPDATE USER")
    @PutMapping("/update")
    @Retry(name = "updateUser",fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> updateUser(@Valid @RequestBody UserDto userDto) {
        boolean isUpdated = userService.updateUser(userDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode("200")
                            .statusMsg("Update request processed successfully")
                            .build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder()
                            .statusCode("417")
                            .statusMsg("Update operation failed")
                            .build());
        }
    }

    /**
     * Delete user response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @Operation(summary = "DELETE USER")
    @DeleteMapping("/delete")
    @Retry(name = "deleteUser",fallbackMethod = "serviceUnavailableFallback")
    public ResponseEntity<ResponseDto> deleteUser(@RequestParam String id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResponseDto.builder()
                            .statusCode("200")
                            .statusMsg("Delete request processed successfully")
                            .build());
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(ResponseDto.builder()
                            .statusCode("417")
                            .statusMsg("Delete operation failed")
                            .build());
        }
    }

    /**
     * Service unavailable fallback response entity.
     *
     * @param ex the exception
     * @return the response entity
     */
    public ResponseEntity<ResponseDto> serviceUnavailableFallback(Exception ex) {
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(
                ResponseDto.builder()
                        .statusCode("503")
                        .statusMsg(ex.getMessage() + " with the error message: " + ex.getCause().getMessage())
                        .build());
    }
}
