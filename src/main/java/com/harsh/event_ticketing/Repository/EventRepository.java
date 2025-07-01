package com.harsh.event_ticketing.Repository;


import com.harsh.event_ticketing.Models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}