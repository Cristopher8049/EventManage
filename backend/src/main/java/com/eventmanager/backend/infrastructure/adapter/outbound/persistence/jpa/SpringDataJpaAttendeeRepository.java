package com.eventmanager.backend.infrastructure.adapter.outbound.persistence.jpa;

import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.entity.AttendeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SpringDataJpaAttendeeRepository
        extends JpaRepository<AttendeeEntity, UUID> {
    List<AttendeeEntity> findAllByEventId(UUID eventId);
}
