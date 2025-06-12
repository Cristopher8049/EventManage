package com.eventmanager.backend.infrastructure.adapter.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "attendees")
@Getter
@Setter
@NoArgsConstructor
public class AttendeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "event_id",       nullable = false)
    private UUID eventId;

    public AttendeeEntity(String name, String email, String phoneNumber, UUID eventId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.eventId = eventId;
    }
}
