package com.eventmanager.backend.application.usecase.event;

import com.eventmanager.backend.application.exception.NotFoundException;
import com.eventmanager.backend.domain.model.Event;
import com.eventmanager.backend.domain.repository.EventRepository;
import com.eventmanager.backend.domain.usecase.event.UpdateEventUseCase;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateEventUseCaseImpl implements UpdateEventUseCase {

    private final EventRepository eventRepository;

    public UpdateEventUseCaseImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event update(UUID id, Event updatedEvent) {
        Event existing = eventRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Event not found: " + id));

        existing.setName(updatedEvent.getName());
        existing.setDescription(updatedEvent.getDescription());
        existing.setStartDateTime(updatedEvent.getStartDateTime());
        existing.setEndDateTime(updatedEvent.getEndDateTime());
        existing.setLocation(updatedEvent.getLocation());
        existing.setCapacity(updatedEvent.getCapacity());

        return eventRepository.update(existing);
    }
}