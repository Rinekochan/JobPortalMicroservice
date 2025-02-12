package com.hoang.user.service;

import com.hoang.user.dto.UserDto;
import com.hoang.user.entity.User;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    UserDto getUser(String id);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<UserDto> getAllUsers();

    /**
     * Create user.
     *
     * @param userDto the userDto
     * @return User user
     */
    User createUser(UserDto userDto);

    /**
     * Update user.
     *
     * @param userDto the userDto
     * @return the boolean
     */
    boolean updateUser(UserDto userDto);

    /**
     * Delete user.
     *
     * @param id the id
     * @return the boolean
     */
    boolean deleteUser(String id);
}
