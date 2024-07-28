package com.homedepot.toolrental.api;

import com.homedepot.toolrental.model.RentalAgreement;
import com.homedepot.toolrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/checkout")
    public RentalAgreement checkOut(@RequestParam String customerId, @RequestParam String toolCode, @RequestParam int rentalDays, @RequestParam int discountPercent, @RequestParam String checkOutDate) {
        LocalDate parsedCheckOutDate = LocalDate.parse(checkOutDate);
        return rentalService.checkOut(customerId, toolCode, rentalDays, discountPercent, parsedCheckOutDate);
    }
}
