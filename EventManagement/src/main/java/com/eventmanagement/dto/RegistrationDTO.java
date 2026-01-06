package com.eventmanagement.dto;

import java.time.LocalDateTime;

public class RegistrationDTO {

    private Long registrationId;
    private String eventTitle;
    private String venue;
    private LocalDateTime registeredAt;

    public RegistrationDTO() {}

    public RegistrationDTO(Long registrationId, String eventTitle,
                           String venue, LocalDateTime registeredAt) {
        this.registrationId = registrationId;
        this.eventTitle = eventTitle;
        this.venue = venue;
        this.registeredAt = registeredAt;
    }

    public Long getRegistrationId() {
        return registrationId;
    }
 
    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }
 
    public String getEventTitle() {
        return eventTitle;
    }
 
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }
 
    public String getVenue() {
        return venue;
    }
 
    public void setVenue(String venue) {
        this.venue = venue;
    }
 
    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }
 
    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }
}
