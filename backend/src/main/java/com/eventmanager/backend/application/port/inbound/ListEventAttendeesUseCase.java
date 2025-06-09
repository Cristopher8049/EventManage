package com.eventmanager.backend.application.port.inbound;

import com.eventmanager.backend.domain.model.attendee.Attendee;
import java.util.List;
import java.util.UUID;

public interface ListEventAttendeesUseCase {
    List<Attendee> getAttendees(UUID eventId);
}
