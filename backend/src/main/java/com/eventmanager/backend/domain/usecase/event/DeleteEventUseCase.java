package com.eventmanager.backend.domain.usecase.event;

import java.util.UUID;

public interface DeleteEventUseCase {
    void deleteById(UUID eventId);
}