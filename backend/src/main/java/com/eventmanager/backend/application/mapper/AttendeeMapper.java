package com.eventmanager.backend.application.mapper;

import com.eventmanager.backend.application.dto.AttendeeDTO;
import com.eventmanager.backend.domain.model.Attendee;
import org.springframework.stereotype.Component;

@Component
public class AttendeeMapper {
    public Attendee toDomain(AttendeeDTO dto) {
        if (dto == null) return null;
        Attendee a = new Attendee();
        a.setFirstName(dto.getFirstName());
        a.setLastName(dto.getLastName());
        a.setEmail(dto.getEmail());
        return a;
    }
}