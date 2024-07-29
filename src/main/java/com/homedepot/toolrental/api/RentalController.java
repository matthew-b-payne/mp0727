package com.homedepot.toolrental.api;

import com.homedepot.toolrental.model.CheckoutRequest;
import com.homedepot.toolrental.model.RentalAgreement;
import com.homedepot.toolrental.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @PostMapping("/checkout")
    public RentalAgreement checkOut(@RequestBody CheckoutRequest request) {
        return rentalService.checkOut(
                request.getCustomerId(),
                request.getToolCode(),
                request.getRentalDays(),
                request.getDiscountPercent(),
                request.getCheckOutDate()
        );
    }
    //    @PostMapping("/checkout")
//    public RentalAgreement checkOut(@RequestParam String customerId, @RequestParam String toolCode, @RequestParam int rentalDays, @RequestParam int discountPercent, @RequestParam String checkOutDate) {
//        LocalDate parsedCheckOutDate = LocalDate.parse(checkOutDate);
//        return rentalService.checkOut(customerId, toolCode, rentalDays, discountPercent, parsedCheckOutDate);
//    }
}
