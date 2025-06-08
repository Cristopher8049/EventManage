package com.eventmanager.backend.application.port.inbound;

import com.eventmanager.backend.domain.model.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventUseCase {
    Event create(Event event);
    Event update(Event event);
    void delete(UUID id);
    Optional<Event> getById(UUID id);
    List<Event> getAll();
}
