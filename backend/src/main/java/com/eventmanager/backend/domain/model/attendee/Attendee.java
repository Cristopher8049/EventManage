package com.eventmanager.backend.domain.model.attendee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Attendee {
    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
    private UUID eventId;

    public Attendee(String name, String email, String phoneNumber, UUID eventId) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.eventId = eventId;
    }
}
