package com.eventmanager.backend.infrastructure.adapter.outbound.persistence.entity;

import com.eventmanager.backend.domain.model.event.FieldType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "event_field_definitions")
@Getter
@Setter
@NoArgsConstructor
public class EventFieldDefinitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "event_id", nullable = false)
    private UUID eventId;

    @Column(nullable = false)
    private String label;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FieldType type;

    @Column(nullable = false)
    private boolean required;

    @Column(name = "order_index", nullable = false)
    private int orderIndex;

    public EventFieldDefinitionEntity(UUID eventId, String label, FieldType type, boolean required, int orderIndex) {
        this.eventId  = eventId;
        this.label    = label;
        this.type     = type;
        this.required = required;
        this.orderIndex = orderIndex;
    }
}
