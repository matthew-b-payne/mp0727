package com.homedepot.toolrental.service;

import com.homedepot.toolrental.model.DayTypesResult;
import com.homedepot.toolrental.model.RentalAgreement;
import com.homedepot.toolrental.model.Tool;
import com.homedepot.toolrental.repository.RentalRepository;
import com.homedepot.toolrental.repository.ToolRepository;
import com.homedepot.toolrental.utils.HolidayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class RentalService {

    private static final Logger logger = LoggerFactory.getLogger(RentalService.class);

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
        DayTypesResult dayTypesResult = HolidayUtil.calculateDayTypes(checkOutDate, dueDate);

        // Calculate charge days (assume all days are chargeable for simplicity)
        int chargeDays = dayTypesResult.getChargeDaysForTool(tool);
        logger.info("Charge days for tool {}: {}", tool.getToolCode(), chargeDays);

        // Calculate pre-discount charge
        BigDecimal preDiscountCharge = calculateCharge(tool, chargeDays);

        // Calculate discount amount
        BigDecimal discountAmount = preDiscountCharge.multiply(BigDecimal.valueOf(discountPercent)).divide(BigDecimal.valueOf(100));
        BigDecimal finalCharge = preDiscountCharge.subtract(discountAmount);
        logger.info("Discount amount: {}, Final charge: {}", discountAmount, finalCharge);

        RentalAgreement rentalAgreement = new RentalAgreement(
                customerId,
                tool.getToolCode(),
                tool.getToolType(),
                tool.getBrand(),
                rentalDays,
                checkOutDate,
                dueDate,
                chargeDays,
                preDiscountCharge,
                discountPercent,
                discountAmount,
                finalCharge
        );
        logger.info("Created RentalAgreement: {}", rentalAgreement);
        return rentalAgreement;
    }

    BigDecimal calculateCharge(Tool tool, int chargeDays) {
        // Calculate pre-discount charge
        BigDecimal chargeAmount = tool.getDailyCharge().multiply(BigDecimal.valueOf(chargeDays));

        return chargeAmount;
    }
}