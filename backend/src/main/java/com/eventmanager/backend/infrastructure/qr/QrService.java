package com.eventmanager.backend.infrastructure.qr;

import com.eventmanager.backend.domain.usecase.GenerateQrUseCase;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;

@Service
public class QrService implements GenerateQrUseCase {

    private final QrGenerator generator = new QrGenerator();

    @Override
    public BufferedImage generate(java.util.UUID ticketId) {
        String payload = ticketId.toString();
        return generator.generateQrImage(payload);
    }

    public String toBase64(BufferedImage image) {
        try {
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            javax.imageio.ImageIO.write(image, "PNG", baos);
            byte[] bytes = baos.toByteArray();
            return java.util.Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            throw new RuntimeException("Error convirtiendo QR a Base64", e);
        }
    }
}