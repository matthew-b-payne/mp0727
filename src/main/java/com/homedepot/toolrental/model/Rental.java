package com.homedepot.toolrental.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Rental {
    @Id
    private String id;
    private String toolId;
    private String userId;
    private Date rentalDate;
    private Date returnDate;
    // getters and setters
}
