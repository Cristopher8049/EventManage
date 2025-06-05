package com.eventmanager.backend.infrastructure.persistence.jpa;

import com.eventmanager.backend.infrastructure.persistence.entity.AttendeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataAttendeeRepository extends JpaRepository<AttendeeEntity, UUID> {
    Optional<AttendeeEntity> findByEmail(String email);
}