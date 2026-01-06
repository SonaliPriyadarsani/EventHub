package com.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventmanagement.model.Event;
import com.eventmanagement.model.Feedback;
import com.eventmanagement.model.User;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.FeedbackRepository;
import com.eventmanagement.repository.UserRepository;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public void submitFeedback(Long eventId, String userEmail,
                               int rating, String comments) {

        User user = userRepository.findByEmail(userEmail).orElse(null);
        Event event = eventRepository.findById(eventId).orElse(null);

        if (user != null && event != null) {
            Feedback feedback = new Feedback(user, event, rating, comments);
            feedbackRepository.save(feedback);
        }
    }

    @Override
    public List<Feedback> getFeedbackByEvent(Long eventId) {

        Event event = eventRepository.findById(eventId).orElse(null);
        return feedbackRepository.findByEvent(event);
    }
    
    @Override
    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
    }
}
