package com.eventmanager.backend.infrastructure.adapter.inbound.rest;

import com.eventmanager.backend.application.port.inbound.AttendeeUseCase;
import com.eventmanager.backend.domain.model.attendee.Attendee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events/{eventId}/attendees")
public class AttendeeController {
    private final AttendeeUseCase useCase;

    public AttendeeController(AttendeeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<Attendee> create(
            @PathVariable UUID eventId,
            @RequestBody Attendee attendee) {
        attendee.setEventId(eventId);
        Attendee created = useCase.create(attendee);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Attendee>> list(
            @PathVariable UUID eventId) {
        List<Attendee> attendees = useCase.getAllByEvent(eventId);
        return ResponseEntity.ok(attendees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendee> get(
            @PathVariable UUID eventId,
            @PathVariable UUID id) {
        return useCase.getById(id)
                .filter(a -> a.getEventId().equals(eventId))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendee> update(
            @PathVariable UUID eventId,
            @PathVariable UUID id,
            @RequestBody Attendee attendee) {
        attendee.setId(id);
        attendee.setEventId(eventId);
        Attendee updated = useCase.update(attendee);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID eventId,
            @PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
