package com.eventmanager.backend.infrastructure.persistence.adapter;

import com.eventmanager.backend.domain.model.Event;
import com.eventmanager.backend.domain.repository.EventRepository;
import com.eventmanager.backend.infrastructure.persistence.entity.EventEntity;
import com.eventmanager.backend.infrastructure.persistence.jpa.SpringDataEventRepository;
import com.eventmanager.backend.infrastructure.persistence.mapper.EventEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EventRepositoryAdapter implements EventRepository {

    private final SpringDataEventRepository springRepo;
    private final EventEntityMapper mapper;

    public EventRepositoryAdapter(SpringDataEventRepository springRepo, EventEntityMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Event save(Event event) {
        EventEntity entity = mapper.toEntity(event);
        EventEntity saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Event update(Event event) {
        EventEntity entity = mapper.toEntity(event);
        EventEntity saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Event> findById(UUID id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Event> findAll() {
        return springRepo.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        springRepo.deleteById(id);
    }
}