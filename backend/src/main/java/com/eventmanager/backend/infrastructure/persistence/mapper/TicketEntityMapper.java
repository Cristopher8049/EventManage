package com.eventmanager.backend.infrastructure.persistence.mapper;

import com.eventmanager.backend.domain.model.Ticket;
import com.eventmanager.backend.infrastructure.persistence.entity.AttendeeEntity;
import com.eventmanager.backend.infrastructure.persistence.entity.EventEntity;
import com.eventmanager.backend.infrastructure.persistence.entity.TicketEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TicketEntityMapper {

    public Ticket toDomain(TicketEntity entity) {
        if (entity == null) return null;
        Ticket domain = new Ticket();
        domain.setId(entity.getId());
        domain.setEventId(entity.getEvent().getId());
        domain.setAttendeeId(entity.getAttendee().getId());
        domain.setIssuedAt(entity.getIssuedAt());
        domain.setQrCodeData(entity.getQrCodeData());
        domain.setUsed(entity.isUsed());
        domain.setUsedAt(entity.getUsedAt());
        return domain;
    }

    public TicketEntity toEntity(Ticket domain) {
        if (domain == null) return null;
        TicketEntity entity = new TicketEntity();
        entity.setId(domain.getId() != null ? domain.getId() : UUID.randomUUID());

        EventEntity ev = new EventEntity();
        ev.setId(domain.getEventId());
        entity.setEvent(ev);

        AttendeeEntity at = new AttendeeEntity();
        at.setId(domain.getAttendeeId());
        entity.setAttendee(at);

        entity.setIssuedAt(domain.getIssuedAt());
        entity.setQrCodeData(domain.getQrCodeData());
        entity.setUsed(domain.isUsed());
        entity.setUsedAt(domain.getUsedAt());
        return entity;
    }
}