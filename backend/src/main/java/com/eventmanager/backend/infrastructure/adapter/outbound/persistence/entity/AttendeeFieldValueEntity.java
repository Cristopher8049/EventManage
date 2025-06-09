package com.eventmanager.backend.infrastructure.adapter.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "attendee_field_values")
@Getter
@Setter
@NoArgsConstructor
public class AttendeeFieldValueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "event_id",       nullable = false)
    private UUID eventId;

    @Column(name = "attendee_id",    nullable = false)
    private UUID attendeeId;

    @Column(name = "field_definition_id", nullable = false)
    private UUID fieldDefinitionId;

    @Column(nullable = false)
    private String value;

    public AttendeeFieldValueEntity(UUID eventId,
                                    UUID attendeeId,
                                    UUID fieldDefinitionId,
                                    String value) {
        this.eventId           = eventId;
        this.attendeeId        = attendeeId;
        this.fieldDefinitionId = fieldDefinitionId;
        this.value             = value;
    }
}
