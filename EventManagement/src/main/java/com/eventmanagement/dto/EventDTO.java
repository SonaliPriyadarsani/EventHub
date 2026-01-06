package com.eventmanagement.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventDTO {

    private Long eventId;
    private String title;
    private String description;
    private String category;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String venue;
    private int availableSeats;
    private String status;

    public EventDTO() {}

    public EventDTO(Long eventId, String title, String description,
                    String category, LocalDate eventDate,
                    LocalTime eventTime, String venue,
                    int availableSeats, String status) {

        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.availableSeats = availableSeats;
        this.status = status;
    }

    public Long getEventId() {
        return eventId;
    }
 
    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }
 
    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getCategory() {
        return category;
    }
 
    public void setCategory(String category) {
        this.category = category;
    }
 
    public LocalDate getEventDate() {
        return eventDate;
    }
 
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
 
    public LocalTime getEventTime() {
        return eventTime;
    }
 
    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }
 
    public String getVenue() {
        return venue;
    }
 
    public void setVenue(String venue) {
        this.venue = venue;
    }
 
    public int getAvailableSeats() {
        return availableSeats;
    }
 
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
}

