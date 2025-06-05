package com.eventmanager.backend.domain.usecase.event;

import com.eventmanager.backend.domain.model.Event;

public interface CreateEventUseCase {
    Event create(Event event);
}