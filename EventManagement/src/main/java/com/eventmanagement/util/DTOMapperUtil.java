package com.eventmanagement.util;

import com.eventmanagement.dto.EventDTO;
import com.eventmanagement.dto.FeedbackDTO;
import com.eventmanagement.dto.UserDTO;
import com.eventmanagement.model.Event;
import com.eventmanagement.model.Feedback;
import com.eventmanagement.model.User;

public class DTOMapperUtil {

    private DTOMapperUtil() {}

    public static UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name(),
                user.getDepartment(),
                user.isActive()
        );
    }

    public static EventDTO mapToEventDTO(Event event) {
        return new EventDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getCategory(),
                event.getDate(),
                event.getTime(),
                event.getVenue(),
                event.getAvailableSeats(),
                event.getStatus().name()
        );
    }

    public static FeedbackDTO mapToFeedbackDTO(Feedback feedback) {
        return new FeedbackDTO(
                feedback.getUser().getName(),
                feedback.getRating(),
                feedback.getComments()
        );
    }
}
