package com.eventmanager.backend.infrastructure.adapter.outbound.persistence;

import com.eventmanager.backend.application.port.outbound.AttendeeRepository;
import com.eventmanager.backend.domain.model.attendee.Attendee;
import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.entity.AttendeeEntity;
import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.jpa.SpringDataJpaAttendeeRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AttendeeRepositoryAdapter implements AttendeeRepository {
    private final SpringDataJpaAttendeeRepository jpa;

    public AttendeeRepositoryAdapter(SpringDataJpaAttendeeRepository jpa) {
        this.jpa = jpa;
    }

    private Attendee toDomain(AttendeeEntity e) {
        Attendee a = new Attendee(e.getName(), e.getEmail(),
                e.getPhoneNumber(), e.getEventId());;
        a.setId(e.getId());
        return a;
    }

    private AttendeeEntity toEntity(Attendee d) {
        AttendeeEntity e = new AttendeeEntity(d.getName(), d.getEmail(), d.getPhoneNumber(), d.getEventId());
        e.setId(d.getId());
        return e;
    }

    @Override
    public Attendee save(Attendee attendee) {
        AttendeeEntity saved = jpa.save(toEntity(attendee));
        return toDomain(saved);
    }

    @Override
    public Optional<Attendee> findById(UUID id) {
        return jpa.findById(id).map(this::toDomain);
    }

    @Override
    public List<Attendee> findAllByEventId(UUID eventId) {
        return jpa.findAllByEventId(eventId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Attendee> findAll() {
        return jpa.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        jpa.deleteById(id);
    }
}
