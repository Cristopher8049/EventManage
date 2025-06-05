package com.eventmanager.backend.domain.usecase;

import com.eventmanager.backend.domain.model.Attendee;
import com.eventmanager.backend.domain.model.Ticket;

import java.util.UUID;

public interface RegisterAttendeeUseCase {
    Ticket register(UUID eventId, Attendee attendee);
}