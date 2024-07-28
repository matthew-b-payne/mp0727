package com.homedepot.toolrental.service;

import com.homedepot.toolrental.model.RentalAgreement;
import com.homedepot.toolrental.model.Tool;
import com.homedepot.toolrental.repository.ToolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RentalServiceTest {

    @Mock
    private ToolRepository toolRepository;

    @InjectMocks
    private RentalService rentalService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInvalidDiscountPercent() { // test1
        String toolCode = "JAKR";
        int rentalDays = 5;
        Tool jackHammer = new Tool("JAKD", "Jackhammer", "Ridgid", new BigDecimal("2.99"),
                true, false, false);
        when(toolRepository.findById(toolCode)).thenReturn(Optional.of(jackHammer));
        LocalDate checkOutDate = LocalDate.of(2015, 9, 3);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            rentalService.checkOut("cust123", toolCode, rentalDays, 101, checkOutDate);
        });

        assertEquals("Discount percent must be between 0 and 100", exception.getMessage());
    }

    @Test
    void test2() {
        String toolCode = "LADW";
        int rentalDays = 3;
        Tool jackHammer = new Tool(toolCode, "Ladder", "Werner", new BigDecimal("1.99"),
                true, true, false);
        when(toolRepository.findById(toolCode)).thenReturn(Optional.of(jackHammer));
        LocalDate checkOutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement rentalAgreement = rentalService.checkOut("cust123", toolCode, rentalDays, 10, checkOutDate);
        assertNotNull(rentalAgreement);
        assertEquals(3, rentalAgreement.getChargeDays());
        assertEquals(new BigDecimal("5.97"), rentalAgreement.getPreDiscountCharge());
        assertEquals(new BigDecimal("5.373"), rentalAgreement.getFinalCharge());
        assertEquals(checkOutDate.plusDays(rentalDays), rentalAgreement.getDueDate());
    }

    @Test
    void test3() {
        String toolCode = "CHNS";
        int rentalDays = 5;
        Tool jackHammer = new Tool(toolCode, "Chainsaw", "Stihl", new BigDecimal("1.49"),
                true, false, true);
        when(toolRepository.findById(toolCode)).thenReturn(Optional.of(jackHammer));
        LocalDate checkOutDate = LocalDate.of(2015, 7, 2);
        RentalAgreement rentalAgreement = rentalService.checkOut("cust123", toolCode, rentalDays, 25, checkOutDate);
        assertNotNull(rentalAgreement);
        assertEquals(4, rentalAgreement.getChargeDays());
        assertEquals(new BigDecimal("5.96"), rentalAgreement.getPreDiscountCharge());
        assertEquals(new BigDecimal("4.47"), rentalAgreement.getFinalCharge());
        assertEquals(checkOutDate.plusDays(rentalDays), rentalAgreement.getDueDate());
    }

    // condition 4
    @Test
    void test4() {  // test 4
        String customerId = "cust123";
        String toolCode = "JAKD";
        int rentalDays = 6;
        int discountPercent = 0;
        LocalDate checkOutDate = LocalDate.of(2024, 7, 25);

        Tool jackHammer = new Tool(toolCode, "Jackhammer", "DeWalt", new BigDecimal("2.99"),
                true, false, false);

        when(toolRepository.findById(toolCode)).thenReturn(Optional.of(jackHammer));

        RentalAgreement rentalAgreement = rentalService.checkOut(customerId, toolCode, rentalDays, discountPercent, checkOutDate);

        assertNotNull(rentalAgreement);
        assertEquals(customerId, rentalAgreement.getCustomerId());
        assertEquals(rentalDays, rentalAgreement.getRentalDays());
        assertEquals(checkOutDate, rentalAgreement.getCheckOutDate());
        assertEquals(checkOutDate.plusDays(rentalDays), rentalAgreement.getDueDate());
        assertEquals(5, rentalAgreement.getChargeDays());
        assertEquals(new BigDecimal("0.00"), rentalAgreement.getDiscountAmount());
        assertEquals(new BigDecimal("14.95"), rentalAgreement.getPreDiscountCharge());
        assertEquals(discountPercent, rentalAgreement.getDiscountPercent());
        verify(toolRepository, times(1)).findById(toolCode);
    }

    @Test
    void test5() {
        String toolCode = "JAKR";
        int rentalDays = 9;
        Tool jackHammer = new Tool(toolCode, "Jackhammer", "DeWalt", new BigDecimal("2.99"),
                true, false, false);
        when(toolRepository.findById(toolCode)).thenReturn(Optional.of(jackHammer));
        LocalDate checkOutDate = LocalDate.of(2015, 7, 2);
        RentalAgreement rentalAgreement = rentalService.checkOut("cust123", toolCode, rentalDays, 0, checkOutDate);
        assertNotNull(rentalAgreement);
        assertEquals(6, rentalAgreement.getChargeDays()); // 9 - 3 (no sat, sun, or application of july 4)
        assertEquals(new BigDecimal("17.94"), rentalAgreement.getPreDiscountCharge());
        assertEquals(checkOutDate.plusDays(rentalDays), rentalAgreement.getDueDate());
    }

    @Test
    void test6() {
        String toolCode = "JAKR";
        int rentalDays = 4;
        Tool jackHammer = new Tool(toolCode, "Jackhammer", "Ridgid", new BigDecimal("2.99"),
                true, false, false);
        when(toolRepository.findById(toolCode)).thenReturn(Optional.of(jackHammer));
        LocalDate checkOutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement rentalAgreement = rentalService.checkOut("cust123", toolCode, rentalDays, 50, checkOutDate);
        assertNotNull(rentalAgreement);
        assertEquals(2, rentalAgreement.getChargeDays()); // 9 - 3 (no sat, sun, or application of july 4)
        assertEquals(new BigDecimal("5.98"), rentalAgreement.getPreDiscountCharge());
        assertEquals(new BigDecimal("2.99"), rentalAgreement.getFinalCharge());
        assertEquals(checkOutDate.plusDays(rentalDays), rentalAgreement.getDueDate());
    }

    @Test
    void testPrintRentalAgreement() {
        String toolCode = "JAKR";
        int rentalDays = 4;
        Tool jackHammer = new Tool(toolCode, "Jackhammer", "Ridgid", new BigDecimal("2.99"),
                true, false, false);
        when(toolRepository.findById(toolCode)).thenReturn(Optional.of(jackHammer));
        LocalDate checkOutDate = LocalDate.of(2020, 7, 2);
        RentalAgreement rentalAgreement = rentalService.checkOut("cust123", toolCode, rentalDays, 50, checkOutDate);
        assertNotNull(rentalAgreement);
        assertEquals(2, rentalAgreement.getChargeDays()); // 9 - 3 (no sat, sun, or application of july 4)
        assertEquals(new BigDecimal("5.98"), rentalAgreement.getPreDiscountCharge());
        assertEquals(new BigDecimal("2.99"), rentalAgreement.getFinalCharge());
        assertEquals(checkOutDate.plusDays(rentalDays), rentalAgreement.getDueDate());
        rentalAgreement.printRentalAgreement();
    }

}