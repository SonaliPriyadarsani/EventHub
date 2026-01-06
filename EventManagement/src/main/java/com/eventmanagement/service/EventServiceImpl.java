package com.eventmanagement.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventmanagement.model.Event;
import com.eventmanagement.model.EventStatus;
import com.eventmanagement.model.Registration;
import com.eventmanagement.model.Role;
import com.eventmanagement.model.User;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.RegistrationRepository;
import com.eventmanagement.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RegistrationRepository registrationRepository;


    @Override
    public Event createEvent(Event event, String creatorEmail) {

        User creator = userRepository.findByEmail(creatorEmail).orElse(null);

        event.setCreatedBy(creator);
        event.setStatus(EventStatus.PENDING);
        event.setAvailableSeats(event.getCapacity());

        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getApprovedEvents() {
        return eventRepository.findByStatus(EventStatus.APPROVED);
    }


    @Override
    public Event approveEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow();
        event.setStatus(EventStatus.APPROVED);
        return eventRepository.save(event);
    }
    @Override
    public Event rejectEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            event.setStatus(EventStatus.REJECTED);
            return eventRepository.save(event);
        }
        return null;
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
    
    @Transactional
    @Override
    public void deleteUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // delete events first
        eventRepository.deleteAll(user.getCreatedEvents());

        // then delete user
        userRepository.delete(user);
    }

    @Override
    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    @Override
    public List<Event> getEventsByCoordinator(String email) {
        return eventRepository.findByCreatedByEmail(email);
    }

       @Override
       public List<Event> getCoordinatorPendingEvents() {
           return eventRepository
               .findByCreatedByRoleAndStatus(Role.COORDINATOR, EventStatus.PENDING);
       }

       @Override
       public void saveEvent(Event event, User coordinator) {
           event.setCreatedBy(coordinator);
           event.setStatus(EventStatus.PENDING);
           eventRepository.save(event);
       }
       
       @Override
       public void updateEvent(Event event) {
           eventRepository.save(event);
       }

       @Override
       public List<Registration> getRegistrationsByEvent(Long eventId) {
           return registrationRepository.findByEventId(eventId);
       }
}
