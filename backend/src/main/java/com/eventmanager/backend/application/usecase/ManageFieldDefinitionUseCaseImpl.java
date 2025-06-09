package com.eventmanager.backend.application.usecase;

import com.eventmanager.backend.application.port.inbound.ManageFieldDefinitionUseCase;
import com.eventmanager.backend.application.port.outbound.EventFieldDefinitionRepository;
import com.eventmanager.backend.domain.model.event.EventFieldDefinition;
import com.eventmanager.backend.domain.model.event.FieldType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ManageFieldDefinitionUseCaseImpl implements ManageFieldDefinitionUseCase {
    private final EventFieldDefinitionRepository repository;

    public ManageFieldDefinitionUseCaseImpl(EventFieldDefinitionRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventFieldDefinition create(UUID eventId, String label, String type, boolean required, int orderIndex) {
        FieldType ft = FieldType.valueOf(type);
        EventFieldDefinition def = new EventFieldDefinition(eventId, label, ft, required, orderIndex);
        return repository.save(def);
    }

    @Override
    public List<EventFieldDefinition> list(UUID eventId) {
        return repository.findByEventId(eventId);
    }

    @Override
    public EventFieldDefinition update(UUID id, String label, String type, boolean required, int orderIndex) {
        EventFieldDefinition def = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Definition not found"));
        def.setLabel(label);
        def.setType(FieldType.valueOf(type));
        def.setRequired(required);
        return repository.save(def);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
