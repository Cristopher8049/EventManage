package com.eventmanager.backend.application.port.inbound;

import com.eventmanager.backend.domain.model.attendee.AttendeeFieldValue;
import java.util.List;
import java.util.UUID;

public interface RegisterAttendeeUseCase {
    List<AttendeeFieldValue> register(UUID eventId, UUID attendeeId, List<AttendeeFieldValue> values);
    List<AttendeeFieldValue> listResponses(UUID eventId);
}