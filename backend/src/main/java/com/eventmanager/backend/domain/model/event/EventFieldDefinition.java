package com.eventmanager.backend.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EventFieldDefinition {
    private UUID id;
    private UUID eventId;
    private String label;
    private FieldType type;
    private boolean required;
    private int orderIndex;

    public EventFieldDefinition(UUID eventId, String label, FieldType type, boolean required, int orderIndex) {
        this.eventId = eventId;
        this.label = label;
        this.type = type;
        this.required = required;
        this.orderIndex = orderIndex;
    }
}
