package com.eventmanager.backend.infrastructure.controller;

import com.eventmanager.backend.application.dto.AttendeeDTO;
import com.eventmanager.backend.application.dto.TicketDTO;
import com.eventmanager.backend.application.mapper.AttendeeMapper;
import com.eventmanager.backend.application.mapper.TicketMapper;
import com.eventmanager.backend.domain.model.Attendee;
import com.eventmanager.backend.domain.model.Ticket;
import com.eventmanager.backend.domain.usecase.RegisterAttendeeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/events/{eventId}/attendees")
public class AttendeeController {

    private final RegisterAttendeeUseCase registerAttendeeUseCase;
    private final AttendeeMapper attendeeMapper;
    private final TicketMapper ticketMapper;

    public AttendeeController(RegisterAttendeeUseCase registerAttendeeUseCase,
                              AttendeeMapper attendeeMapper,
                              TicketMapper ticketMapper) {
        this.registerAttendeeUseCase = registerAttendeeUseCase;
        this.attendeeMapper = attendeeMapper;
        this.ticketMapper = ticketMapper;
    }

    @PostMapping
    public ResponseEntity<TicketDTO> registerAttendee(@PathVariable UUID eventId,
                                                      @Valid @RequestBody AttendeeDTO attendeeDto) {
        Attendee domain = attendeeMapper.toDomain(attendeeDto);
        Ticket ticket = registerAttendeeUseCase.register(eventId, domain);
        return ResponseEntity.ok(ticketMapper.toDTO(ticket));
    }
}