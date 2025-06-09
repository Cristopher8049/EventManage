package com.eventmanager.backend.infrastructure.adapter.outbound.persistence.jpa;

import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.entity.EventFieldDefinitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataJpaEventFieldDefinitionRepository
        extends JpaRepository<EventFieldDefinitionEntity, UUID> {
    List<EventFieldDefinitionEntity> findByEventId(UUID eventId);
}