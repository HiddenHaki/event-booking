package com.harsh.event_ticketing.Controller;

import com.harsh.event_ticketing.Models.Event;
import com.harsh.event_ticketing.Repository.EventRepository;
import org.springframework.ui.Model;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String home(Model model) {
        try {
            if (eventRepository.count() == 0) {
                Event e1 = new Event();
                e1.setTitle("Spring boot workshop");
                e1.setVenue("Online");
                e1.setDate(LocalDate.of(2025, 7, 1));
                e1.setTotalTickets(100);
                e1.setAvailableTickets(100);

                Event e2 = new Event();
                e2.setTitle("DefCon");
                e2.setVenue("Mumbai");
                e2.setDate(LocalDate.of(2025, 8, 2));
                e2.setTotalTickets(200);
                e2.setAvailableTickets(200);

                eventRepository.saveAll(List.of(e1, e2));
            }

            List<Event> events = eventRepository.findAll();
            model.addAttribute("events", events);

            return "home";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

}
