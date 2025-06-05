package com.eventmanager.backend.infrastructure.persistence.adapter;

import com.eventmanager.backend.domain.model.Attendee;
import com.eventmanager.backend.domain.repository.AttendeeRepository;
import com.eventmanager.backend.infrastructure.persistence.entity.AttendeeEntity;
import com.eventmanager.backend.infrastructure.persistence.jpa.SpringDataAttendeeRepository;
import com.eventmanager.backend.infrastructure.persistence.mapper.AttendeeEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AttendeeRepositoryAdapter implements AttendeeRepository {

    private final SpringDataAttendeeRepository springRepo;
    private final AttendeeEntityMapper mapper;

    public AttendeeRepositoryAdapter(SpringDataAttendeeRepository springRepo, AttendeeEntityMapper mapper) {
        this.springRepo = springRepo;
        this.mapper = mapper;
    }

    @Override
    public Attendee save(Attendee attendee) {
        AttendeeEntity entity = mapper.toEntity(attendee);
        AttendeeEntity saved = springRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Attendee> findById(UUID id) {
        return springRepo.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Attendee> findByEmail(String email) {
        return springRepo.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public List<Attendee> findAll() {
        return springRepo.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(UUID id) {
        springRepo.deleteById(id);
    }
}