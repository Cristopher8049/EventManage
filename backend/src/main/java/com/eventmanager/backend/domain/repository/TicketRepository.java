package com.eventmanager.backend.domain.repository;

import com.eventmanager.backend.domain.model.Ticket;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketRepository {
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(UUID id);
    List<Ticket> findByEventId(UUID eventId);
    long countByEventId(UUID eventId);
    boolean existsByEventIdAndAttendeeEmail(UUID eventId, String attendeeEmail);
    void deleteById(UUID id);
}