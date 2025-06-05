package com.eventmanager.backend.infrastructure.persistence.jpa;

import com.eventmanager.backend.infrastructure.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataEventRepository extends JpaRepository<EventEntity, UUID> {
}