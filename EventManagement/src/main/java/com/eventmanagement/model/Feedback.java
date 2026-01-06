package com.eventmanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    private int rating; // 1 to 5

    @Column(length = 1000)
    private String comments;

    // Constructors
    public Feedback() {}

    public Feedback(User user, Event event, int rating, String comments) {
        this.user = user;
        this.event = event;
        this.rating = rating;
        this.comments = comments;
    }

    // Getters and Setters
    public Long getFeedbackId() {
        return feedbackId;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComments() {
        return comments;
    }
 
    public void setComments(String comments) {
        this.comments = comments;
    }
}
