package com.homedepot.toolrental.repository;

import com.homedepot.toolrental.model.Tool;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToolRepository extends MongoRepository<Tool, String> {
}

