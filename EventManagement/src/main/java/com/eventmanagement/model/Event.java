package com.eventmanagement.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private String category;

    private LocalDate date;

    private LocalTime time;

    private String venue;

    private int capacity;
    private String coordinator;

    private int availableSeats;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
    @OneToMany(mappedBy = "event",
            cascade = CascadeType.ALL,
            orphanRemoval = true)

    private List<EventRegistration> registrations = new ArrayList<>();

    // ✅ VERY IMPORTANT GETTER
    public List<EventRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<EventRegistration> registrations) {
        this.registrations = registrations;
    }

    // Constructors
    public Event() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
 
    public LocalDate getDate() {
        return date;
    }
 
    public void setDate(LocalDate date) {
        this.date = date;
    }
 
    public LocalTime getTime() {
        return time;
    }
 
    public void setTime(LocalTime time) {
        this.time = time;
    }
 
    public String getVenue() {
        return venue;
    }
 
    public void setVenue(String venue) {
        this.venue = venue;
    }
 
    public int getCapacity() {
        return capacity;
    }
 
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
 
    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public EventStatus getStatus() {
        return status;
    }
 
    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public User getCreatedBy() {
        return createdBy;
    }
 
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

	public String getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(String coordinator) {
		this.coordinator = coordinator;
	}
}
