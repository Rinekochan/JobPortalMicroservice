package com.hoang.user.service;

import com.hoang.user.constant.Constants;
import com.hoang.user.dto.UserDto;
import com.hoang.user.entity.User;
import com.hoang.user.exception.ResourceNotFoundException;
import com.hoang.user.mapper.UserMapper;
import com.hoang.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type User service.
 */
@AllArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto getUser(String id) {
        User user = userRepository.getUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.USER_TAG, "id", id));

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::mapToUserDto)
                .toList();
    }

    @Override
    public User createUser(UserDto userDto) {
        return userRepository.save(UserMapper.mapToUser(userDto));
    }

    @Override
    public boolean updateUser(UserDto userDto) {
        boolean isUpdated = false;

        userRepository.getUserById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException(Constants.USER_TAG, "ID", userDto.getId())
        );

        userRepository.save(UserMapper.mapToUser(userDto));
        return !isUpdated;
    }

    @Override
    public boolean deleteUser(String id) {
        boolean isDeleted = false;

        User user = userRepository.getUserById(id).orElseThrow(
                () -> new ResourceNotFoundException(Constants.USER_TAG, "ID", id)
        );

        userRepository.deleteUserById(user.getId());

        return !isDeleted;
    }
}
