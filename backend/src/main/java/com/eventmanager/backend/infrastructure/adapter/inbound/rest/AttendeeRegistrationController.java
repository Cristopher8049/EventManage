package com.eventmanager.backend.infrastructure.adapter.inbound.rest;


import com.eventmanager.backend.application.port.inbound.RegisterAttendeeUseCase;
import com.eventmanager.backend.domain.model.attendee.AttendeeFieldValue;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events/{eventId}/attendees/{attendeeId}/responses")
public class AttendeeRegistrationController {

    private final RegisterAttendeeUseCase service;

    public AttendeeRegistrationController(RegisterAttendeeUseCase service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<List<AttendeeFieldValue>> register(
            @PathVariable UUID eventId,
            @PathVariable UUID attendeeId,
            @RequestBody List<FieldValueRequest> reqs
    ) {
        List<AttendeeFieldValue> values = reqs.stream()
                .map(r -> new AttendeeFieldValue(
                        eventId, attendeeId,
                        r.getFieldDefinitionId(),
                        r.getValue()
                ))
                .collect(Collectors.toList());

        List<AttendeeFieldValue> saved = service.register(eventId, attendeeId, values);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<AttendeeFieldValue>> listResponses(
            @PathVariable UUID eventId
    ) {
        List<AttendeeFieldValue> responses = service.listResponses(eventId);
        return ResponseEntity.ok(responses);
    }

    @Data
    public static class FieldValueRequest {
        private UUID fieldDefinitionId;
        private String value;
    }
}
