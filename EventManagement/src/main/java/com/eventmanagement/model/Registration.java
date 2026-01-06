package com.eventmanagement.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "registrations",
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "event_id"}))
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private LocalDateTime registeredAt;

    // Constructors
    public Registration() {}

    public Registration(User user, Event event) {
        this.user = user;
        this.event = event;
        this.registeredAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getRegistrationId() {
        return registrationId;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegistrationId(Long registrationId) {
        this.registrationId = registrationId;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
