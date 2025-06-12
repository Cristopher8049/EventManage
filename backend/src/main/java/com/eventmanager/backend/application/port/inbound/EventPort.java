package com.eventmanager.backend.application.port.inbound;

import com.eventmanager.backend.domain.model.event.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventPort {
    Event createEvent(Event event);
    Event updateEvent(Event event);
    void deleteEvent(UUID id);
    Optional<Event> getById(UUID id);
    List<Event> getAll();
}
