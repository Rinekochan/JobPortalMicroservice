package com.hoang.user.repository;

import com.hoang.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> getUserById(String id);

    void deleteUserById(String id);
}
