package com.eventmanager.backend.application.mapper;

import com.eventmanager.backend.application.dto.EventDTO;
import com.eventmanager.backend.domain.model.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {
    public Event toDomain(EventDTO dto) {
        if (dto == null) return null;
        Event e = new Event();
        e.setId(dto.getId());
        e.setName(dto.getName());
        e.setDescription(dto.getDescription());
        e.setStartDateTime(dto.getStartDateTime());
        e.setEndDateTime(dto.getEndDateTime());
        e.setLocation(dto.getLocation());
        e.setCapacity(dto.getCapacity());
        e.setCreatedAt(dto.getCreatedAt());
        return e;
    }

    public EventDTO toDTO(Event domain) {
        if (domain == null) return null;
        EventDTO dto = new EventDTO();
        dto.setId(domain.getId());
        dto.setName(domain.getName());
        dto.setDescription(domain.getDescription());
        dto.setStartDateTime(domain.getStartDateTime());
        dto.setEndDateTime(domain.getEndDateTime());
        dto.setLocation(domain.getLocation());
        dto.setCapacity(domain.getCapacity());
        dto.setCreatedAt(domain.getCreatedAt());
        return dto;
    }
}