package com.homedepot.toolrental.repository;

import com.homedepot.toolrental.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
