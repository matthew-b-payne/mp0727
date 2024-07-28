package com.homedepot.toolrental.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.lang.System.out;

@Document(collection = "rentalAgreements")
public class RentalAgreement {

    @Id
    private String id;

    private String customerId; // this might be an object depending on the requirement
    private String toolCode; // this might be an object depending on the requirement
    private String toolType;
    private String toolBrand;

    private Integer rentalDays;
    private LocalDate checkOutDate;
    private LocalDate dueDate;
    private Integer chargeDays;
    private BigDecimal preDiscountCharge;
    private Integer discountPercent;
    private BigDecimal discountAmount;
    private BigDecimal finalCharge;

    public RentalAgreement() {

    }

    public RentalAgreement(String customerId, String toolCode, String toolType, String toolBrand, Integer rentalDays,
                           LocalDate checkOutDate, LocalDate dueDate, Integer chargeDays, BigDecimal preDiscountCharge,
                           Integer discountPercent, BigDecimal discountAmount, BigDecimal finalCharge) {
        this.customerId = customerId;
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.toolBrand = toolBrand;
        this.rentalDays = rentalDays;
        this.checkOutDate = checkOutDate;
        this.dueDate = dueDate;
        this.chargeDays = chargeDays;
        this.preDiscountCharge = preDiscountCharge;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.finalCharge = finalCharge;
    }


    public void printRentalAgreement() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat percentFormatter = NumberFormat.getPercentInstance(Locale.US);
        percentFormatter.setMaximumFractionDigits(0);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Tool code: %s\n", toolCode));
        sb.append(String.format("Tool type: %s\n", toolType));
        sb.append(String.format("Tool brand: %s\n", toolBrand));
        sb.append(String.format("Rental days: %d\n", rentalDays));
        sb.append(String.format("Check out date: %s\n", checkOutDate.format(dateFormatter)));
        sb.append(String.format("Due date: %s\n", dueDate.format(dateFormatter)));
        sb.append(String.format("Daily rental charge: %s\n", currencyFormatter.format(preDiscountCharge.divide(BigDecimal.valueOf(chargeDays)))));
        sb.append(String.format("Charge days: %d\n", chargeDays));
        sb.append(String.format("Pre-discount charge: %s\n", currencyFormatter.format(preDiscountCharge)));
        sb.append(String.format("Discount percent: %s\n", percentFormatter.format(discountPercent / 100.0)));
        sb.append(String.format("Discount amount: %s\n", currencyFormatter.format(discountAmount)));
        sb.append(String.format("Final charge: %s\n", currencyFormatter.format(finalCharge)));

        out.println(sb.toString());
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public Integer getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getChargeDays() {
        return chargeDays;
    }

    public void setChargeDays(Integer chargeDays) {
        this.chargeDays = chargeDays;
    }

    public BigDecimal getPreDiscountCharge() {
        return preDiscountCharge;
    }

    public void setPreDiscountCharge(BigDecimal preDiscountCharge) {
        this.preDiscountCharge = preDiscountCharge;
    }

    public Integer getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getFinalCharge() {
        return finalCharge;
    }

    public void setFinalCharge(BigDecimal finalCharge) {
        this.finalCharge = finalCharge;
    }

    public String getToolType() {
        return toolType;
    }

    public void setToolType(String toolType) {
        this.toolType = toolType;
    }

    public String getToolBrand() {
        return toolBrand;
    }

    public void setToolBrand(String toolBrand) {
        this.toolBrand = toolBrand;
    }
}
