package com.eventmanager.backend.infrastructure.adapter.outbound.persistence;


import com.eventmanager.backend.application.port.outbound.EventFieldDefinitionRepository;
import com.eventmanager.backend.domain.model.event.EventFieldDefinition;
import com.eventmanager.backend.domain.model.event.FieldType;
import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.entity.EventFieldDefinitionEntity;
import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.jpa.SpringDataJpaEventFieldDefinitionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class EventFieldDefinitionRepositoryAdapter implements EventFieldDefinitionRepository {
    private final SpringDataJpaEventFieldDefinitionRepository jpaRepo;

    public EventFieldDefinitionRepositoryAdapter(SpringDataJpaEventFieldDefinitionRepository jpaRepo) {
        this.jpaRepo = jpaRepo;
    }

    private EventFieldDefinition toDomain(EventFieldDefinitionEntity e) {
        EventFieldDefinition def = new EventFieldDefinition(
                e.getEventId(),
                e.getLabel(),
                e.getType(),
                e.isRequired(),
                e.getOrderIndex()
        );
        def.setId(e.getId());
        return def;
    }

    private EventFieldDefinitionEntity toEntity(EventFieldDefinition d) {
        EventFieldDefinitionEntity e = new EventFieldDefinitionEntity(
                d.getEventId(),
                d.getLabel(),
                FieldType.valueOf(d.getType().name()),
                d.isRequired(),
                d.getOrderIndex()
        );
        e.setId(d.getId());
        return e;
    }

    @Override
    public EventFieldDefinition save(EventFieldDefinition def) {
        EventFieldDefinitionEntity saved = jpaRepo.save(toEntity(def));
        return toDomain(saved);
    }

    @Override
    public List<EventFieldDefinition> findByEventId(UUID eventId) {
        return jpaRepo.findByEventId(eventId)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EventFieldDefinition> findById(UUID id) {
        return jpaRepo.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepo.deleteById(id);
    }
}

