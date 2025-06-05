package com.eventmanager.backend.application.usecase.ticket;

import com.eventmanager.backend.domain.model.Ticket;
import com.eventmanager.backend.domain.repository.TicketRepository;
import com.eventmanager.backend.domain.usecase.ValidateTicketUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ValidateTicketUseCaseImpl implements ValidateTicketUseCase {

    private final TicketRepository ticketRepository;

    public ValidateTicketUseCaseImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public boolean validate(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no existe"));

        if (ticket.isUsed()) {
            return false;
        }

        ticket.setUsed(true);
        ticket.setUsedAt(LocalDateTime.now());
        ticketRepository.save(ticket);
        return true;
    }
}