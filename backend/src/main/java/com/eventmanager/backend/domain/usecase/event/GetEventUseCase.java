package com.eventmanager.backend.domain.usecase.event;

import com.eventmanager.backend.domain.model.Event;

import java.util.Optional;
import java.util.UUID;

public interface GetEventUseCase {
    Optional<Event> findById(UUID eventId);
}