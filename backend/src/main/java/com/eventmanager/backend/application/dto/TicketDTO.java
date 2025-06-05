package com.eventmanager.backend.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class TicketDTO {
    private UUID id;
    private UUID eventId;
    private UUID attendeeId;
    private LocalDateTime issuedAt;
}