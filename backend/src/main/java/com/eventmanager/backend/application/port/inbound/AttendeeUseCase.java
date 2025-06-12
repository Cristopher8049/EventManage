package com.eventmanager.backend.application.port.inbound;

import com.eventmanager.backend.domain.model.attendee.Attendee;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendeeUseCase {
    Attendee create(Attendee attendee);
    List<Attendee> getAllByEvent(UUID eventId);
    Optional<Attendee> getById(UUID attendeeId);
    Attendee update(Attendee attendee);
    void delete(UUID attendeeId);
}
