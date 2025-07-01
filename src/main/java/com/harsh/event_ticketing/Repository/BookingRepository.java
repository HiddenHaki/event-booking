package com.harsh.event_ticketing.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.harsh.event_ticketing.Models.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    
}