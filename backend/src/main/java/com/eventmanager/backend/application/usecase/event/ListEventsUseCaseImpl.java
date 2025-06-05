package com.eventmanager.backend.application.usecase.event;

import com.eventmanager.backend.domain.model.Event;
import com.eventmanager.backend.domain.repository.EventRepository;
import com.eventmanager.backend.domain.usecase.event.ListEventsUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListEventsUseCaseImpl implements ListEventsUseCase {

    private final EventRepository eventRepository;

    public ListEventsUseCaseImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }
}