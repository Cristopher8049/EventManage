package com.eventmanager.backend.infrastructure.persistence.jpa;

import com.eventmanager.backend.infrastructure.persistence.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpringDataTicketRepository extends JpaRepository<TicketEntity, UUID> {

    List<TicketEntity> findByEventId(UUID eventId);

    @Query("SELECT COUNT(t) FROM TicketEntity t WHERE t.event.id = ?1")
    long countByEventId(UUID eventId);

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END " +
            "FROM TicketEntity t WHERE t.event.id = ?1 AND t.attendee.email = ?2")
    boolean existsByEventIdAndAttendeeEmail(UUID eventId, String email);
}