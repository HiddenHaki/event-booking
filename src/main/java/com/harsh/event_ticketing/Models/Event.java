package com.harsh.event_ticketing.Models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String venue;
    private LocalDate date;
    private int totalTickets;
    private int availableTickets;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    // Getters and setters
}
