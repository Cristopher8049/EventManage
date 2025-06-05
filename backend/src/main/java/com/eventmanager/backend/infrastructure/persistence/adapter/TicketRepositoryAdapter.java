package com.eventmanager.backend.infrastructure.persistence.adapter;

import com.eventmanager.backend.domain.model.Ticket;
import com.eventmanager.backend.domain.repository.TicketRepository;
import com.eventmanager.backend.infrastructure.persistence.entity.TicketEntity;
import com.eventmanager.backend.infrastructure.persistence.jpa.SpringDataTicketRepository;
import com.eventmanager.backend.infrastructure.persistence.mapper.TicketEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class TicketRepositoryAdapter implements TicketRepository {

    private final SpringDataTicketRepository springRepo;
    private final TicketEntityMapper mapper;

    public TicketRepositoryAdapter(SpringDataTicketRepository springRepo, TicketEntityMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Ticket save(Ticket ticket) {
        TicketEntity entity = mapper.toEntity(ticket);
        TicketEntity saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Ticket> findById(UUID id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Ticket> findByEventId(UUID eventId) {
        return springRepo.findByEventId(eventId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long countByEventId(UUID eventId) {
        return springRepo.countByEventId(eventId);
    }

    @Override
    public boolean existsByEventIdAndAttendeeEmail(UUID eventId, String attendeeEmail) {
        return springRepo.existsByEventIdAndAttendeeEmail(eventId, attendeeEmail);
    }

    @Override
    public void deleteById(UUID id) {
        springRepo.deleteById(id);
    }
}