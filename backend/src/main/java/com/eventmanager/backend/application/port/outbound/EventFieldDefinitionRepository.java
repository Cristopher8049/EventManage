package com.eventmanager.backend.application.port.outbound;

import com.eventmanager.backend.domain.model.event.EventFieldDefinition;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventFieldDefinitionRepository {
    EventFieldDefinition save(EventFieldDefinition def);
    List<EventFieldDefinition> findByEventId(UUID eventId);
    Optional<EventFieldDefinition> findById(UUID id);
    void deleteById(UUID id);
}
