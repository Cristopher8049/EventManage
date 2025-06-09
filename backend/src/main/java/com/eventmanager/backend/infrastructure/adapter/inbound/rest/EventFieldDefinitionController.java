package com.eventmanager.backend.infrastructure.adapter.inbound.rest;


import com.eventmanager.backend.application.port.inbound.ManageFieldDefinitionUseCase;
import com.eventmanager.backend.domain.model.event.EventFieldDefinition;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events/{eventId}/fields")
public class EventFieldDefinitionController {

    private final ManageFieldDefinitionUseCase service;

    public EventFieldDefinitionController(ManageFieldDefinitionUseCase service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EventFieldDefinition> create(
            @PathVariable UUID eventId,
            @RequestBody FieldDefinitionRequest req
    ) {
        EventFieldDefinition def = service.create(
                eventId, req.getLabel(), req.getType(), req.isRequired(), req.getOrderIndex()
        );
        return ResponseEntity.ok(def);
    }

    @GetMapping
    public ResponseEntity<List<EventFieldDefinition>> list(
            @PathVariable UUID eventId
    ) {
        List<EventFieldDefinition> defs = service.list(eventId);
        return ResponseEntity.ok(defs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventFieldDefinition> update(
            @PathVariable UUID id,
            @RequestBody FieldDefinitionRequest req
    ) {
        EventFieldDefinition def = service.update(
                id, req.getLabel(), req.getType(), req.isRequired(), req.getOrderIndex()
        );
        return ResponseEntity.ok(def);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Data
    public static class FieldDefinitionRequest {
        private String label;
        private String type;
        private boolean required;
        private int orderIndex;
    }
}

