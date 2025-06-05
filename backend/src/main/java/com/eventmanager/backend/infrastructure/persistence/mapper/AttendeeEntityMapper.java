package com.eventmanager.backend.infrastructure.persistence.mapper;

import com.eventmanager.backend.domain.model.Attendee;
import com.eventmanager.backend.infrastructure.persistence.entity.AttendeeEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AttendeeEntityMapper {

    public Attendee toDomain(AttendeeEntity entity) {
        if (entity == null) return null;
        Attendee domain = new Attendee();
        domain.setId(entity.getId());
        domain.setFirstName(entity.getFirstName());
        domain.setLastName(entity.getLastName());
        domain.setEmail(entity.getEmail());
        return domain;
    }

    public AttendeeEntity toEntity(Attendee domain) {
        if (domain == null) return null;
        AttendeeEntity entity = new AttendeeEntity();
        entity.setId(domain.getId() != null ? domain.getId() : UUID.randomUUID());
        entity.setFirstName(domain.getFirstName());
        entity.setLastName(domain.getLastName());
        entity.setEmail(domain.getEmail());
        return entity;
    }
}