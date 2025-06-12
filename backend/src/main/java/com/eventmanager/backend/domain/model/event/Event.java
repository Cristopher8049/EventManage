package com.eventmanager.backend.domain.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
        this.status = EventStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();

        validateStartDateNotPast();
        validateStartBeforeEnd();
        isMondayToFriday(startDateTime, endDateTime);
    }

    private int daysBetweenDates(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ( int) ChronoUnit.DAYS.between(startDateTime, endDateTime);

    }

    private void isMondayToFriday(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        int days = daysBetweenDates(startDateTime, startDateTime) + 1;
        int count =  startDateTime.getDayOfWeek().getValue();
        for(int i = startDateTime.getDayOfWeek().getValue(); i <= days; i++){

            if(count == 7){
                count = 0;
            }
            if(count >=1 && count <=5 ){
                setStatus(EventStatus.APPROVED);
            }
            count++;
        }
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
