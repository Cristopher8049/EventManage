package com.eventmanager.backend.application.port.outbound;

import com.eventmanager.backend.domain.model.attendee.AttendeeFieldValue;
import java.util.List;
import java.util.UUID;

public interface AttendeeFieldValueRepository {
    List<AttendeeFieldValue> saveAll(List<AttendeeFieldValue> values);
    List<AttendeeFieldValue> findByEventId(UUID eventId);
}