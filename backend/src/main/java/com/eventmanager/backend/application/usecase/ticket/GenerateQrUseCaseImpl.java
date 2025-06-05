package com.eventmanager.backend.application.usecase.ticket;

import com.eventmanager.backend.domain.model.Ticket;
import com.eventmanager.backend.domain.repository.TicketRepository;
import com.eventmanager.backend.domain.usecase.GenerateQrUseCase;
import com.eventmanager.backend.infrastructure.qr.QrService;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.UUID;

@Service
public class GenerateQrUseCaseImpl implements GenerateQrUseCase {

    private final TicketRepository ticketRepository;
    private final QrService qrService;

    public GenerateQrUseCaseImpl(TicketRepository ticketRepository, QrService qrService) {
        this.ticketRepository = ticketRepository;
        this.qrService = qrService;
    }

    @Override
    public BufferedImage generate(UUID ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        BufferedImage qrImage = qrService.generate(ticketId);

        String payload = ticket.getId().toString();
        ticket.setQrCodeData(payload);
        ticketRepository.save(ticket);

        return qrImage;
    }
}