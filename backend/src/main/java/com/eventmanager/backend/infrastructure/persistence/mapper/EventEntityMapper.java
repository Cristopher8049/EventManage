package com.eventmanager.backend.infrastructure.persistence.mapper;

import com.eventmanager.backend.domain.model.Event;
import com.eventmanager.backend.infrastructure.persistence.entity.EventEntity;
import org.springframework.stereotype.Component;

@Component
public class EventEntityMapper {

    public Event toDomain(EventEntity entity) {
        if (entity == null) return null;
        Event domain = new Event();
        domain.setId(entity.getId());
        domain.setName(entity.getName());
        domain.setDescription(entity.getDescription());
        domain.setStartDateTime(entity.getStartDateTime());
        domain.setEndDateTime(entity.getEndDateTime());
        domain.setLocation(entity.getLocation());
        domain.setCapacity(entity.getCapacity());
        domain.setCreatedAt(entity.getCreatedAt());
        return domain;
    }

    public EventEntity toEntity(Event domain) {
        if (domain == null) return null;
        EventEntity entity = new EventEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        entity.setStartDateTime(domain.getStartDateTime());
        entity.setEndDateTime(domain.getEndDateTime());
        entity.setLocation(domain.getLocation());
        entity.setCapacity(domain.getCapacity());
        entity.setCreatedAt(domain.getCreatedAt());
        return entity;
    }
}