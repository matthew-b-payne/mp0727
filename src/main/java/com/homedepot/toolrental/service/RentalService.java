package com.homedepot.toolrental.service;

import com.homedepot.toolrental.model.RentalAgreement;
import com.homedepot.toolrental.model.Tool;
import com.homedepot.toolrental.repository.RentalRepository;
import com.homedepot.toolrental.repository.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class RentalService {

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public RentalAgreement checkOut(String customerId, String toolCode, int rentalDays, int discountPercent, LocalDate checkOutDate) {
        // Fetch tool details using the toolCode
        Tool tool = toolRepository.findById(toolCode).orElseThrow(() -> new RuntimeException("Tool not found"));

        // Perform required checks
        if (rentalDays <= 0) {
            throw new IllegalArgumentException("Rental days must be positive");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Discount percent must be between 0 and 100");
        }

        // Calculate due date
        LocalDate dueDate = checkOutDate.plusDays(rentalDays);

        // Calculate charge days (assume all days are chargeable for simplicity)
        int chargeDays = rentalDays;

        // Calculate pre-discount charge
        BigDecimal preDiscountCharge = tool.getDailyCharge().multiply(BigDecimal.valueOf(chargeDays));

        // Calculate discount amount
        BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(100));


        RentalAgreement rentalAgreement = new RentalAgreement(
                customerId,
                rentalDays,
                checkOutDate,
                dueDate,
                chargeDays,
                preDiscountCharge,
                discountPercent,
                discountAmount
        );

        return rentalAgreement;
    }
}