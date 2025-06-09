package com.eventmanager.backend.infrastructure.adapter.outbound.persistence.jpa;

import com.eventmanager.backend.infrastructure.adapter.outbound.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SpringDataJpaEventRepository extends JpaRepository<EventEntity, UUID> {
}