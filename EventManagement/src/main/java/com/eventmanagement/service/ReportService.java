package com.eventmanagement.service;

import java.util.Map;

public interface ReportService {

    long getTotalUsers();

    long getTotalEvents();

    long getTotalRegistrations();

    Map<String, Long> getRegistrationsPerEvent();
}
