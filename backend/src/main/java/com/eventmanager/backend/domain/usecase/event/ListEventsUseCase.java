package com.eventmanager.backend.domain.usecase.event;

import com.eventmanager.backend.domain.model.Event;

import java.util.List;

public interface ListEventsUseCase {
    List<Event> listAll();
}