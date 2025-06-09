package com.eventmanager.backend.infrastructure.adapter.inbound.rest;

import com.eventmanager.backend.application.port.inbound.AttendeeUseCase;
import com.eventmanager.backend.domain.model.attendee.Attendee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/attendees")
public class AttendeeController {
    private final AttendeeUseCase useCase;

    public AttendeeController(AttendeeUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<Attendee> create(@RequestBody Attendee a) {
        return ResponseEntity.ok(useCase.create(a));
    }

    @GetMapping
    public ResponseEntity<List<Attendee>> list() {
        return ResponseEntity.ok(useCase.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendee> get(@PathVariable UUID id) {
        return useCase.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attendee> update(@PathVariable UUID id,
                                           @RequestBody Attendee a) {
        a.setId(id);
        return ResponseEntity.ok(useCase.update(a));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        useCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
