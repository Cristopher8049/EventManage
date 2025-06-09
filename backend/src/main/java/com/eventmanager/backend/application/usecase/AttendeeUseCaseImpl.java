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
    private final AttendeeRepository repo;

    public AttendeeUseCaseImpl(AttendeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Attendee create(Attendee attendee) {
        return repo.save(attendee);
    }

    @Override
    public Attendee update(Attendee attendee) {
        return repo.save(attendee);
    }

    @Override
    public Optional<Attendee> getById(UUID id) {
        return repo.findById(id);
    }

    @Override
    public List<Attendee> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(UUID id) {
        repo.deleteById(id);
    }
}
