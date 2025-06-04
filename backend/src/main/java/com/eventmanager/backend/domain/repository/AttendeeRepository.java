package com.eventmanager.backend.domain.repository;

import com.eventmanager.backend.domain.model.Attendee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendeeRepository {
    Attendee save(Attendee attendee);
    Optional<Attendee> findById(UUID id);
    Optional<Attendee> findByEmail(String email);
    List<Attendee> findAll();
    void deleteById(UUID id);
}