package com.eventmanager.backend.infrastructure.adapter.outbound.persistence;

import com.eventmanager.backend.application.port.outbound.EventRepository;
import com.eventmanager.backend.domain.model.event.Event;
import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.entity.EventEntity;
import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.jpa.SpringDataJpaEventRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EventRepositoryAdapter implements EventRepository {
    private final SpringDataJpaEventRepository jpaRepo;

    public EventRepositoryAdapter(SpringDataJpaEventRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    private Event toDomain(EventEntity e) {
        Event evt = new Event(
                e.getTitle(), e.getDescription(),
                e.getStartDateTime(), e.getEndDateTime(),
                e.getLocation(), e.getMaxCapacity()
        );
        evt.setId(e.getId());
        evt.setCurrentAttendees(e.getCurrentAttendees());
        evt.setStatus(e.getStatus());
        evt.setCreatedAt(e.getCreatedAt());
        evt.setUpdatedAt(e.getUpdatedAt());
        return evt;
    }

    private EventEntity toEntity(Event d) {
        return new EventEntity(
                d.getId(), d.getTitle(), d.getDescription(),
                d.getStartDateTime(), d.getEndDateTime(),
                d.getLocation(), d.getMaxCapacity(), d.getCurrentAttendees(),
                d.getStatus(), d.getCreatedAt(), d.getUpdatedAt()
        );
    }

    @Override
    public Event save(Event event) {
        EventEntity saved = jpaRepo.save(toEntity(event));
        return toDomain(saved);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepo.deleteById(id);
    }

    @Override
    public Optional<Event> findById(UUID id) {
        return jpaRepo.findById(id).map(this::toDomain);
    }

    @Override
    public List<Event> findAll() {
        return jpaRepo.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}