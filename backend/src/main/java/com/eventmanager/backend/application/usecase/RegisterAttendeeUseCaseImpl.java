package com.eventmanager.backend.application.usecase;


import com.eventmanager.backend.application.port.inbound.RegisterAttendeeUseCase;
import com.eventmanager.backend.application.port.outbound.AttendeeFieldValueRepository;
import com.eventmanager.backend.application.port.outbound.EventFieldDefinitionRepository;
import com.eventmanager.backend.domain.model.attendee.AttendeeFieldValue;
import com.eventmanager.backend.domain.model.event.EventFieldDefinition;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegisterAttendeeUseCaseImpl implements RegisterAttendeeUseCase {
    private final AttendeeFieldValueRepository valRepo;
    private final EventFieldDefinitionRepository defRepo;

    public RegisterAttendeeUseCaseImpl(AttendeeFieldValueRepository valRepo,
                                   EventFieldDefinitionRepository defRepo) {
        this.valRepo = valRepo;
        this.defRepo = defRepo;
    }

    @Override
    public List<AttendeeFieldValue> register(UUID eventId, UUID attendeeId, List<AttendeeFieldValue> values) {
        List<UUID> validDefs = defRepo.findByEventId(eventId)
                .stream().map(EventFieldDefinition::getId).toList();
        values.forEach(v -> {
            if (!validDefs.contains(v.getEventFieldDefinitionId())) {
                throw new IllegalArgumentException("FieldDefinition not valid for this event");
            }
            v.setEventId(eventId);
            v.setAttendeeId(attendeeId);
        });
        return valRepo.saveAll(values);
    }

    @Override
    public List<AttendeeFieldValue> listResponses(UUID eventId) {
        return valRepo.findByEventId(eventId);
    }
}