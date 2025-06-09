package com.eventmanager.backend.application.usecase;


import com.eventmanager.backend.application.port.inbound.ListEventAttendeesUseCase;
import com.eventmanager.backend.application.port.outbound.AttendeeFieldValueRepository;
import com.eventmanager.backend.application.port.outbound.AttendeeRepository;
import com.eventmanager.backend.domain.model.attendee.Attendee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListEventAttendeesUseCaseImpl implements ListEventAttendeesUseCase {
    private final AttendeeFieldValueRepository valuesRepo;
    private final AttendeeRepository attendeeRepo;

    public ListEventAttendeesUseCaseImpl(AttendeeFieldValueRepository valuesRepo,
                                     AttendeeRepository attendeeRepo) {
        this.valuesRepo    = valuesRepo;
        this.attendeeRepo  = attendeeRepo;
    }

    @Override
    public List<Attendee> getAttendees(UUID eventId) {
        return valuesRepo.findByEventId(eventId).stream()
                .map(v -> v.getAttendeeId())
                .distinct()
                .map(attendeeRepo::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}

