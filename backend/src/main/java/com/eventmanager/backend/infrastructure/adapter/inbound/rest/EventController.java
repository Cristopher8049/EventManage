package com.eventmanager.backend.infrastructure.adapter.inbound.rest;

import com.eventmanager.backend.application.port.inbound.EventUseCase;
import com.eventmanager.backend.domain.model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventUseCase service;

    public EventController(EventUseCase service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        Event created = service.create(event);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Event>> list() {
        List<Event> events = service.getAll();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable UUID id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable UUID id, @RequestBody Event event) {
        // opcional: asegurarse de que el ID del body coincida con el path
        event.setId(id);
        Event updated = service.update(event);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
