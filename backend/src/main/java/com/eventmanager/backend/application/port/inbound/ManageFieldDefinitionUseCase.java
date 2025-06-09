package com.eventmanager.backend.application.port.inbound;

import com.eventmanager.backend.domain.model.event.EventFieldDefinition;
import java.util.List;
import java.util.UUID;

public interface ManageFieldDefinitionUseCase {
    EventFieldDefinition create(UUID eventId, String label, String type, boolean required, int orderIndex);
    List<EventFieldDefinition> list(UUID eventId);
    EventFieldDefinition update(UUID id, String label, String type, boolean required, int orderIndex);
    void delete(UUID id);
}