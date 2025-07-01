package com.harsh.event_ticketing.Repository;


import com.harsh.event_ticketing.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
