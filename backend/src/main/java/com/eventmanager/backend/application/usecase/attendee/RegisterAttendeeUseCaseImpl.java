package com.eventmanager.backend.application.usecase.attendee;

import com.eventmanager.backend.application.exception.DomainException;
import com.eventmanager.backend.application.exception.NotFoundException;
import com.eventmanager.backend.domain.model.Attendee;
import com.eventmanager.backend.domain.model.Event;
import com.eventmanager.backend.domain.model.Ticket;
import com.eventmanager.backend.domain.repository.AttendeeRepository;
import com.eventmanager.backend.domain.repository.EventRepository;
import com.eventmanager.backend.domain.repository.TicketRepository;
import com.eventmanager.backend.domain.usecase.RegisterAttendeeUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RegisterAttendeeUseCaseImpl implements RegisterAttendeeUseCase {

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    private final TicketRepository ticketRepository;

    public RegisterAttendeeUseCaseImpl(EventRepository eventRepository,
                                       AttendeeRepository attendeeRepository,
                                       TicketRepository ticketRepository) {
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket register(UUID eventId, Attendee attendee) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Evento no encontrado"));

        long inscritos = ticketRepository.countByEventId(eventId);
        if (inscritos >= event.getCapacity()) {
            throw new DomainException("Evento con cupos agotados");
        }

        boolean exists = ticketRepository.existsByEventIdAndAttendeeEmail(eventId, attendee.getEmail());
        if (exists) {
            throw new DomainException("El email ya está registrado para este evento");
        }

        attendee.setId(UUID.randomUUID());
        Attendee savedAttendee = attendeeRepository.save(attendee);

        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID());
        ticket.setEventId(eventId);
        ticket.setAttendeeId(savedAttendee.getId());
        ticket.setIssuedAt(LocalDateTime.now());
        ticket.setUsed(false);
        ticket.setQrCodeData("");
        return ticketRepository.save(ticket);
    }
}