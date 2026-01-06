package com.eventmanagement.service;

import java.util.List;

import com.eventmanagement.model.Feedback;

public interface FeedbackService {

    void submitFeedback(Long eventId, String userEmail,
                        int rating, String comments);

    List<Feedback> getFeedbackByEvent(Long eventId);

	void saveFeedback(Feedback feedback);
}
