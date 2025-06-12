//package com.eventmanager.backend.infrastructure.adapter.inbound.rest;
//
//import com.eventmanager.backend.application.port.inbound.ListEventAttendeesUseCase;
//import com.eventmanager.backend.domain.model.attendee.Attendee;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/events/{eventId}/attendees")
//public class EventAttendeeController {
//    private final ListEventAttendeesUseCase useCase;
//
//    public EventAttendeeController(ListEventAttendeesUseCase useCase) {
//        this.useCase = useCase;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Attendee>> list(@PathVariable UUID eventId) {
//        return ResponseEntity.ok(useCase.getAttendees(eventId));
//    }
//}
