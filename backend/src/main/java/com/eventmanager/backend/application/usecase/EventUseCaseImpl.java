package com.eventmanager.backend.application.usecase;

import com.eventmanager.backend.application.port.inbound.EventUseCase;
import com.eventmanager.backend.application.port.outbound.EventRepository;
import com.eventmanager.backend.domain.model.event.Event;
import com.eventmanager.backend.domain.exception.BusinessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventUseCaseImpl implements EventUseCase {
    private final EventRepository repository;

    public EventUseCaseImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event create(Event event) {
        repository.findAll().stream()
                .filter(e -> e.getTitle().equalsIgnoreCase(event.getTitle()))
                .findAny()
                .ifPresent(e -> { throw new BusinessException("Título ya existe"); });
        event.setCreatedAt(LocalDateTime.now());
        return repository.save(event);
    }

    @Override
    public Event update(Event event) {
        event.setUpdatedAt(LocalDateTime.now());
        return repository.save(event);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Event> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Event> getAll() {
        return repository.findAll();
    }
}
