package com.harsh.event_ticketing.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.harsh.event_ticketing.Models.Booking;
import com.harsh.event_ticketing.Repository.BookingRepository;

@Controller
public class AdminController {
    
    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/admin/bookings")
    public String viewAllBooking(Model model) {
        List<Booking> bookings = bookingRepository.findAll();
        model.addAttribute("bookings", bookings);
        return "admin-bookings";
    }

}
