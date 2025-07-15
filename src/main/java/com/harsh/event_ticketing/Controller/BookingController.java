package com.harsh.event_ticketing.Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.harsh.event_ticketing.Models.Booking;
import com.harsh.event_ticketing.Models.Event;
import com.harsh.event_ticketing.Repository.BookingRepository;
import com.harsh.event_ticketing.Repository.EventRepository;

@Controller
public class BookingController {
    @Autowired

    private BookingRepository bookingRepository;
    @Autowired
    private EventRepository eventRepository;

    // Show Booking form for an event
    @GetMapping("/booking/{id}")
    public String showBookingForm(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id).orElse(null);

        if (event == null || event.getAvailableTickets() <= 0) {
            model.addAttribute("error", "Sold Out / Event not found");
        }

        if(event.getDate().isBefore(LocalDate.now())){
            model.addAttribute("error", "Booking is closed for this event");
            return "error";
        }

        Booking booking = new Booking();
        booking.setEvent(event); // link booking with event
        model.addAttribute("booking", booking);

        return "booking-form";
    }

    @PostMapping("/booking")
    public String processBooking(Booking booking, Model model) {
        // Fetch the event from database using the event ID
        Event event = null;
        if (booking.getEvent() != null && booking.getEvent().getId() != null) {
            event = eventRepository.findById(booking.getEvent().getId()).orElse(null);
            booking.setEvent(event); // Set the complete event object
        }

        // show error if tickets are not available
        if (event == null || booking.getNumberOfTickets() > event.getAvailableTickets()) {
            model.addAttribute("error", "Not enough tickets available");
            return "error";
        }

        // Reduce available tickets
        event.setAvailableTickets(event.getAvailableTickets() - booking.getNumberOfTickets());
        booking.setBookingTime(LocalDateTime.now());

        // now save the updated booking and event
        bookingRepository.save(booking);
        eventRepository.save(event);

        model.addAttribute("message", "Booking Successful!");

        return "booking-success";
    }

}
