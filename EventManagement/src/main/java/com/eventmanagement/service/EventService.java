package com.eventmanagement.service;

import java.util.List;
import com.eventmanagement.model.Event;
import com.eventmanagement.model.Registration;
import com.eventmanagement.model.User;

public interface EventService {

    Event createEvent(Event event, String creatorEmail);

    List<Event> getAllEvents();

    List<Event> getApprovedEvents();

    Event approveEvent(Long eventId);

    Event rejectEvent(Long eventId);

    Event getEventById(Long eventId);

    List<Event> getEventsByCoordinator(String email);

	void deleteEvent(Long id);

	void saveEvent(Event event, User coordinator);

	List<Event> getCoordinatorPendingEvents();

    void updateEvent(Event event);

    List<Registration> getRegistrationsByEvent(Long eventId);

	void deleteUserById(Long userId);
}
