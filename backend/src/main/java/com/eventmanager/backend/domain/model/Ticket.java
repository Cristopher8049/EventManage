package com.eventmanager.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private UUID id;
    private UUID eventId;
    private UUID attendeeId;
    private LocalDateTime issuedAt;
    private String qrCodeData;
    private boolean used;
    private LocalDateTime usedAt;
}