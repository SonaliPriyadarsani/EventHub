package com.eventmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventmanagement.model.Event;
import com.eventmanagement.model.Registration;
import com.eventmanagement.model.User;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.RegistrationRepository;
import com.eventmanagement.repository.UserRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public String registerForEvent(Long eventId, String userEmail) {

        User user = userRepository.findByEmail(userEmail).orElse(null);
        Event event = eventRepository.findById(eventId).orElse(null);

        if (user == null || event == null) {
            return "Invalid user or event";
        }

        if (event.getAvailableSeats() <= 0) {
            return "No seats available";
        }

        if (registrationRepository.findByUserAndEvent(user, event).isPresent()) {
            return "Already registered for this event";
        }

        Registration registration = new Registration(user, event);
        registrationRepository.save(registration);

        event.setAvailableSeats(event.getAvailableSeats() - 1);
        eventRepository.save(event);

        return "Registration successful";
    }
    
    @Override
    public void register(Long eventId, String studentEmail) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        User student = userRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Registration registration = new Registration();
        registration.setEvent(event);
        registration.setUser(student);

        registrationRepository.save(registration);
    }
    @Override
    public List<Registration> getRegistrationsByUser(String userEmail) {

        User user = userRepository.findByEmail(userEmail).orElse(null);
        return registrationRepository.findByUser(user);
    }
}
