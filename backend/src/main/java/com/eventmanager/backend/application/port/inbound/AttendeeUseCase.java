package com.eventmanager.backend.application.port.inbound;

import com.eventmanager.backend.domain.model.attendee.Attendee;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendeeUseCase {
    Attendee create(Attendee attendee);
    Attendee update(Attendee attendee);
    Optional<Attendee> getById(UUID id);
    List<Attendee> getAll();
    void delete(UUID id);
}
