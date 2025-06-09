package com.eventmanager.backend.application.port.outbound;

import com.eventmanager.backend.domain.model.event.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository {
    Event save(Event event);
    void deleteById(UUID id);
    Optional<Event> findById(UUID id);
    List<Event> findAll();

}
