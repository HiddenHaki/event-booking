package com.harsh.event_ticketing.Models;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private String role; // USER or ADMIN
    // user can have multiple bookings.. 
                //booked by user ,  if user deleted, bookings deleted too. 
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Booking> bookings = new ArrayList<>();

    // Getters and setters
}
