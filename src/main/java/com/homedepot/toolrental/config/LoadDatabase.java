package com.homedepot.toolrental.config;

import com.homedepot.toolrental.model.RentalAgreement;
import com.homedepot.toolrental.model.Tool;
import com.homedepot.toolrental.repository.RentalRepository;
import com.homedepot.toolrental.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class LoadDatabase {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Check if the database is already populated
            if (toolRepository.count() == 0) {
                toolRepository.save(new Tool("CHNS", "Chainsaw", "Stihl", new BigDecimal("1.49")));
//                toolRepository.save(new Tool("LADW", "Saw", "S001", new BigDecimal("2.99")));
                toolRepository.save(new Tool("LADW", "Ladder", "Werner", new BigDecimal("1.99")));
                toolRepository.save(new Tool("JAKD", "Jackhammer", "DeWalt", new BigDecimal("2.99")));
                toolRepository.save(new Tool("JAKR", "Jackhammer", "Ridgid", new BigDecimal("2.99")));
            }
//
//            if (rentalRepository.count() == 0) {
//                rentalRepository.save(new RentalAgreement("cust1", 3, LocalDate.now(), LocalDate.now().plusDays(3), 3, new BigDecimal("5.97"), 10, new BigDecimal("0.60")));
//            }
        };
    }
}