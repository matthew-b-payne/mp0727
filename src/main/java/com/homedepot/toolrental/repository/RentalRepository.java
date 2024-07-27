package com.homedepot.toolrental.repository;

import com.homedepot.toolrental.model.Rental;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RentalRepository extends MongoRepository<Rental, String> {
}
