package com.eventmanager.backend.infrastructure.adapter.outbound.persistence;

import com.eventmanager.backend.application.port.outbound.AttendeeFieldValueRepository;
import com.eventmanager.backend.domain.model.attendee.AttendeeFieldValue;
import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.entity.AttendeeFieldValueEntity;
import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.jpa.SpringDataJpaAttendeeFieldValueRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AttendeeFieldValueRepositoryAdapter implements AttendeeFieldValueRepository {
    private final SpringDataJpaAttendeeFieldValueRepository jpaRepo;

    public AttendeeFieldValueRepositoryAdapter(SpringDataJpaAttendeeFieldValueRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    private AttendeeFieldValue toDomain(AttendeeFieldValueEntity e) {
        AttendeeFieldValue v = new AttendeeFieldValue(
                e.getEventId(),
                e.getAttendeeId(),
                e.getFieldDefinitionId(),
                e.getValue()
        );
        v.setId(e.getId());
        return v;
    }

    private AttendeeFieldValueEntity toEntity(AttendeeFieldValue d) {
        AttendeeFieldValueEntity e = new AttendeeFieldValueEntity(
                d.getEventId(),
                d.getAttendeeId(),
                d.getEventFieldDefinitionId(),
                d.getValue()
        );
        e.setId(d.getId());
        return e;
    }

    @Override
    public List<AttendeeFieldValue> saveAll(List<AttendeeFieldValue> values) {
        return jpaRepo.saveAll(values.stream()
                        .map(this::toEntity)
                        .collect(Collectors.toList()))
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendeeFieldValue> findByEventId(UUID eventId) {
        return jpaRepo.findByEventId(eventId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
}
