package com.eventmanager.backend.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Event {
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String location;
    private int maxCapacity;
    private int currentAttendees;
    private EventStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Event(String title, String description,
                 LocalDateTime startDateTime, LocalDateTime endDateTime,
                 String location, int maxCapacity) {
        this.title = title;
        this.description = description;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.location = location;
        this.maxCapacity = maxCapacity;
        this.currentAttendees = 0;
        this.status = EventStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        validateStartDateNotPast();
        validateStartBeforeEnd();
    }

    private void validateStartDateNotPast(){
        if(startDateTime.isBefore(LocalDateTime.now())){
            throw new IllegalArgumentException("Start date cannot be in the past.");
        }
    }

    private void validateStartBeforeEnd(){
        if(startDateTime.isAfter(endDateTime)){
            throw new IllegalArgumentException("Start date must be before end date.");
        }
    }

}
