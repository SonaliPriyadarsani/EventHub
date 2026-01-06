package com.eventmanagement.dto;

public class FeedbackDTO {

    private String userName;
    private int rating;
    private String comments;

    public FeedbackDTO() {}

    public FeedbackDTO(String userName, int rating, String comments) {
        this.userName = userName;
        this.rating = rating;
        this.comments = comments;
    }

    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
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
