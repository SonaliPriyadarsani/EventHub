package com.eventmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventmanagement.model.Event;
import com.eventmanagement.repository.EventRepository;
import com.eventmanagement.repository.RegistrationRepository;
import com.eventmanagement.repository.UserRepository;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RegistrationRepository registrationRepository;

    @Override
    public long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    public long getTotalEvents() {
        return eventRepository.count();
    }

    @Override
    public long getTotalRegistrations() {
        return registrationRepository.count();
    }

    @Override
    public Map<String, Long> getRegistrationsPerEvent() {

        Map<String, Long> report = new HashMap<>();
        List<Event> events = eventRepository.findAll();

        for (Event event : events) {
            long count = registrationRepository.findAll()
                    .stream()
                    .filter(r -> r.getEvent().getId()
                            .equals(event.getId()))
                    .count();

            report.put(event.getTitle(), count);
        }
        return report;
    }
}
