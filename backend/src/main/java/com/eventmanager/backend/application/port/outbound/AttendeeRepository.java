package com.eventmanager.backend.application.port.outbound;

import com.eventmanager.backend.domain.model.attendee.Attendee;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttendeeRepository {
    Attendee save(Attendee attendee);
    Optional<Attendee> findById(UUID id);
    List<Attendee> findAllByEventId(UUID eventId);
    List<Attendee> findAll();
    void deleteById(UUID id);
}

