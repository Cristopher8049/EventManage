package com.eventmanager.backend.infrastructure.config;

import com.eventmanager.backend.application.usecase.attendee.RegisterAttendeeUseCaseImpl;
import com.eventmanager.backend.application.usecase.event.CreateEventUseCaseImpl;
import com.eventmanager.backend.application.usecase.event.DeleteEventUseCaseImpl;
import com.eventmanager.backend.application.usecase.event.GetEventUseCaseImpl;
import com.eventmanager.backend.application.usecase.event.ListEventsUseCaseImpl;
import com.eventmanager.backend.application.usecase.ticket.GenerateQrUseCaseImpl;
import com.eventmanager.backend.application.usecase.ticket.ValidateTicketUseCaseImpl;
import com.eventmanager.backend.domain.repository.AttendeeRepository;
import com.eventmanager.backend.domain.repository.EventRepository;
import com.eventmanager.backend.domain.repository.TicketRepository;
import com.eventmanager.backend.domain.usecase.*;
import com.eventmanager.backend.domain.usecase.event.CreateEventUseCase;
import com.eventmanager.backend.domain.usecase.event.DeleteEventUseCase;
import com.eventmanager.backend.domain.usecase.event.GetEventUseCase;
import com.eventmanager.backend.domain.usecase.event.ListEventsUseCase;
import com.eventmanager.backend.infrastructure.qr.QrService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final EventRepository eventRepository;
    private final AttendeeRepository attendeeRepository;
    private final TicketRepository ticketRepository;
    private final QrService qrService;

    public SpringConfig(EventRepository eventRepository,
                        AttendeeRepository attendeeRepository,
                        TicketRepository ticketRepository,
                        QrService qrService) {
        this.eventRepository = eventRepository;
        this.attendeeRepository = attendeeRepository;
        this.ticketRepository = ticketRepository;
        this.qrService = qrService;
    }

    @Bean
    public CreateEventUseCase createEventUseCase() {
        return new CreateEventUseCaseImpl(eventRepository);
    }

    @Bean
    public ListEventsUseCase listEventsUseCase() {
        return new ListEventsUseCaseImpl(eventRepository);
    }

    @Bean
    public GetEventUseCase getEventUseCase() {
        return new GetEventUseCaseImpl(eventRepository);
    }

    @Bean
    public DeleteEventUseCase deleteEventUseCase() {
        return new DeleteEventUseCaseImpl(eventRepository);
    }

    @Bean
    public RegisterAttendeeUseCase registerAttendeeUseCase() {
        return new RegisterAttendeeUseCaseImpl(eventRepository, attendeeRepository, ticketRepository);
    }

    @Bean
    public com.eventmanager.backend.domain.usecase.GenerateQrUseCase generateQrUseCase() {
        return new GenerateQrUseCaseImpl(ticketRepository, qrService);
    }

    @Bean
    public ValidateTicketUseCase validateTicketUseCase() {
        return new ValidateTicketUseCaseImpl(ticketRepository);
    }
}