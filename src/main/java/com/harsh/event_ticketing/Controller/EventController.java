package com.harsh.event_ticketing.Controller;

import com.harsh.event_ticketing.Models.Event;
import com.harsh.event_ticketing.Repository.EventRepository;
import org.springframework.ui.Model;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Event> events = eventRepository.findAll();

        model.addAttribute("events", events);

        return "home";
    }

    @GetMapping("/admin/events/new")
    public String showAddEventForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("mode", "add");
        return "add-event";
    }

    @PostMapping("/admin/events/save")
    public String saveOrUpdateEvent(@ModelAttribute Event event) {

        if (event.getId() != null) {
            Event existing = eventRepository.findById(event.getId()).orElse(null);
            if (existing != null) {
                int booked = existing.getTotalTickets() - existing.getAvailableTickets();
                existing.setTitle(event.getTitle());
                existing.setVenue(event.getVenue());
                existing.setDate(event.getDate());
                existing.setTotalTickets(event.getTotalTickets());

                existing.setAvailableTickets(Math.max(0, event.getTotalTickets() - booked));
                eventRepository.save(existing);
                return "redirect:/admin/events";
            }
        }

        event.setAvailableTickets(event.getTotalTickets());
        eventRepository.save(event);
        return "redirect:/admin/events";
    }

    @GetMapping("admin/events/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);

        return "redirect:/admin/events";
    }

    @GetMapping("admin/events")
    public String showAllEventsForAdmin(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "admin-events";
    }

    @GetMapping("admin/events/edit/{id}")
    public String editEvent(@PathVariable Long id, Model model) {
        Event event = eventRepository.findById(id).orElse(null);

        if (event == null) {
            model.addAttribute("error", event);
            return "error";
        }
        model.addAttribute("event", event);
        model.addAttribute("mode", "edit");
        return "add-event";

    }
    
    @GetMapping("/admin")
    public String adminDashboard() {
        return "admin-dashboard";
    }

}
