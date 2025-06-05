package com.eventmanager.backend.application.mapper;

import com.eventmanager.backend.application.dto.TicketDTO;
import com.eventmanager.backend.domain.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {
    public TicketDTO toDTO(Ticket domain) {
        if (domain == null) return null;
        TicketDTO dto = new TicketDTO();
        dto.setId(domain.getId());
        dto.setEventId(domain.getEventId());
        dto.setAttendeeId(domain.getAttendeeId());
        dto.setIssuedAt(domain.getIssuedAt());
        return dto;
    }
}