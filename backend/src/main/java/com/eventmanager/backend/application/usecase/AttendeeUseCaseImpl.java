package com.eventmanager.backend.application.usecase;

import com.eventmanager.backend.application.port.inbound.AttendeeUseCase;
import com.eventmanager.backend.application.port.outbound.AttendeeRepository;
import com.eventmanager.backend.domain.model.attendee.Attendee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AttendeeUseCaseImpl implements AttendeeUseCase {

    private final AttendeeRepository repository;

    public AttendeeUseCaseImpl(AttendeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Attendee create(Attendee attendee) {
        return repository.save(attendee);
    }

    @Override
    public List<Attendee> getAllByEvent(UUID eventId) {
        return repository.findAllByEventId(eventId);
    }

    @Override
    public Optional<Attendee> getById(UUID attendeeId) {
        return repository.findById(attendeeId);
    }

    @Override
    public Attendee update(Attendee attendee) {
        return repository.save(attendee);
    }

    @Override
    public void delete(UUID attendeeId) {
        repository.deleteById(attendeeId);
    }
}
