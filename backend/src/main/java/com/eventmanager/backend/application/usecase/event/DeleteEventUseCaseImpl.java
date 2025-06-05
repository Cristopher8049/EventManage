package com.eventmanager.backend.application.usecase.event;

import com.eventmanager.backend.domain.repository.EventRepository;
import com.eventmanager.backend.domain.usecase.event.DeleteEventUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteEventUseCaseImpl implements DeleteEventUseCase {

    private final EventRepository eventRepository;

    public DeleteEventUseCaseImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void deleteById(UUID eventId) {
        eventRepository.deleteById(eventId);
    }
}