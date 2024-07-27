package com.homedepot.toolrental.repository;

import com.homedepot.toolrental.model.Rental;
import com.homedepot.toolrental.model.Tool;
import com.homedepot.toolrental.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToolRepository extends MongoRepository<Tool, String> {
}

