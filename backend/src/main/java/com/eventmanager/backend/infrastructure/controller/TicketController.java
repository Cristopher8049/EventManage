package com.eventmanager.backend.infrastructure.controller;

import com.eventmanager.backend.application.dto.QrResponseDTO;
import com.eventmanager.backend.domain.usecase.GenerateQrUseCase;
import com.eventmanager.backend.domain.usecase.ValidateTicketUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final GenerateQrUseCase generateQrUseCase;
    private final ValidateTicketUseCase validateTicketUseCase;

    public TicketController(GenerateQrUseCase generateQrUseCase,
                            ValidateTicketUseCase validateTicketUseCase) {
        this.generateQrUseCase = generateQrUseCase;
        this.validateTicketUseCase = validateTicketUseCase;
    }

    @GetMapping("/{ticketId}/qr")
    public ResponseEntity<QrResponseDTO> getQrForTicket(@PathVariable UUID ticketId) {
        BufferedImage qrImage = generateQrUseCase.generate(ticketId);
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "PNG", baos);
            String base64 = Base64.getEncoder().encodeToString(baos.toByteArray());
            QrResponseDTO dto = new QrResponseDTO();
            dto.setQrBase64("data:image/png;base64," + base64);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/{ticketId}/validate")
    public ResponseEntity<Void> validateTicket(@PathVariable UUID ticketId) {
        boolean valid = validateTicketUseCase.validate(ticketId);
        if (valid) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}