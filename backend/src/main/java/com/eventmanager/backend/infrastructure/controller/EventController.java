package com.eventmanager.backend.infrastructure.controller;

import com.eventmanager.backend.application.dto.EventDTO;
import com.eventmanager.backend.application.exception.NotFoundException;
import com.eventmanager.backend.application.mapper.EventMapper;
import com.eventmanager.backend.domain.model.Event;
import com.eventmanager.backend.domain.usecase.event.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final CreateEventUseCase createEventUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final ListEventsUseCase listEventsUseCase;
    private final GetEventUseCase getEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;
    private final EventMapper mapper;

    public EventController(CreateEventUseCase createEventUseCase, UpdateEventUseCase updateEventUseCase,
                           ListEventsUseCase listEventsUseCase,
                           GetEventUseCase getEventUseCase,
                           DeleteEventUseCase deleteEventUseCase,
                           EventMapper mapper) {
        this.createEventUseCase = createEventUseCase;
        this.updateEventUseCase = updateEventUseCase;
        this.listEventsUseCase = listEventsUseCase;
        this.getEventUseCase = getEventUseCase;
        this.deleteEventUseCase = deleteEventUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<EventDTO> createEvent(@Valid @RequestBody EventDTO eventDto) {
        Event domain = mapper.toDomain(eventDto);
        Event saved = createEventUseCase.create(domain);
        return ResponseEntity.ok(mapper.toDTO(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> updateEvent(
            @PathVariable("id") UUID id,
            @RequestBody @Valid EventDTO eventDTO) {
        Event updatedDomain = mapper.toDomain(eventDTO);
        Event saved = updateEventUseCase.update(id, updatedDomain);
        EventDTO responseDto = mapper.toDTO(saved);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAllEvents() {
        List<Event> events = listEventsUseCase.listAll();
        List<EventDTO> dtos = events.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getEventById(@PathVariable UUID id) {
        Event event = getEventUseCase.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento no encontrado"));
        return ResponseEntity.ok(mapper.toDTO(event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        deleteEventUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}