package com.eventmanager.backend.domain.model.attendee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AttendeeFieldValue {
    private UUID id;
    private UUID eventId;
    private UUID attendeeId;
    private UUID eventFieldDefinitionId;
    private String value;

    public AttendeeFieldValue(UUID eventId, UUID attendeeId, UUID eventFieldDefinitionId, String value) {
        this.eventId = eventId;
        this.attendeeId = attendeeId;
        this.eventFieldDefinitionId = eventFieldDefinitionId;
        this.value = value;
    }
}
