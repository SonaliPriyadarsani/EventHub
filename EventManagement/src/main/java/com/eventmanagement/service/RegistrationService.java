package com.eventmanagement.service;

import java.util.List;

import com.eventmanagement.model.Registration;

public interface RegistrationService {

    String registerForEvent(Long eventId, String userEmail);

    List<Registration> getRegistrationsByUser(String userEmail);

	void register(Long id, String studentEmail);
}
