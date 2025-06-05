package com.eventmanager.backend.domain.usecase.event;

import com.eventmanager.backend.domain.model.Event;

import java.util.UUID;

public interface UpdateEventUseCase {
    Event update(UUID id, Event updatedEvent);
}