package com.eventmanager.backend.infrastructure.adapter.outbound.persistence.jpa;

import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.entity.AttendeeFieldValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataJpaAttendeeFieldValueRepository
        extends JpaRepository<AttendeeFieldValueEntity, UUID> {
    List<AttendeeFieldValueEntity> findByEventId(UUID eventId);
}

