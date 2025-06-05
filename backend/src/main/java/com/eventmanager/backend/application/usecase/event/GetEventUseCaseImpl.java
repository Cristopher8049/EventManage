package com.eventmanager.backend.application.usecase.event;

import com.eventmanager.backend.domain.model.Event;
import com.eventmanager.backend.domain.repository.EventRepository;
import com.eventmanager.backend.domain.usecase.event.GetEventUseCase;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetEventUseCaseImpl implements GetEventUseCase {

    private final EventRepository eventRepository;

    public GetEventUseCaseImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Optional<Event> findById(UUID eventId) {
        return eventRepository.findById(eventId);
    }
}