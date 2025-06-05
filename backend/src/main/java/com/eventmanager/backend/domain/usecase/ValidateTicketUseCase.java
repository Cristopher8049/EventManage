package com.eventmanager.backend.domain.usecase;

import java.util.UUID;

public interface ValidateTicketUseCase {
    boolean validate(UUID ticketId);
}