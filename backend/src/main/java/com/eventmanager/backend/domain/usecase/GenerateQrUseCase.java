package com.eventmanager.backend.domain.usecase;

import java.awt.image.BufferedImage;
import java.util.UUID;

public interface GenerateQrUseCase {
    BufferedImage generate(UUID ticketId);
}