package com.eventmanager.backend.application.usecase.event;

import com.eventmanager.backend.domain.model.Event;
import com.eventmanager.backend.domain.repository.EventRepository;
import com.eventmanager.backend.domain.usecase.event.CreateEventUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final EventRepository eventRepository;

    public CreateEventUseCaseImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event create(Event event) {
        event.setId(UUID.randomUUID());
        event.setCreatedAt(LocalDateTime.now());
        return eventRepository.save(event);
    }
}