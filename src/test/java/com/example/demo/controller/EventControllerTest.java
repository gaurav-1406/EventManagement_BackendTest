package com.example.demo.controller;

import com.example.demo.model.Event;
import com.example.demo.model.Participant;
import com.example.demo.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EventControllerTest {
    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEvent() {
        Event event = new Event();
        when(eventService.createEvent(event)).thenReturn(event);

        Event createdEvent = eventController.createEvent(event);

        assertEquals(event, createdEvent);
        verify(eventService, times(1)).createEvent(event);
    }

    @Test
    void testGetAllEvents() {
        List<Event> events = new ArrayList<>();
        when(eventService.getAllEvents()).thenReturn(events);

        List<Event> retrievedEvents = eventController.getAllEvents();

        assertEquals(events, retrievedEvents);
        verify(eventService, times(1)).getAllEvents();
    }

    @Test
    void testGetEventsByLocation() {
        String location = "New York";
        List<Event> events = new ArrayList<>();
        when(eventService.getEventsByLocation(location)).thenReturn(events);

        List<Event> retrievedEvents = eventController.getEventsByLocation(location);

        assertEquals(events, retrievedEvents);
        verify(eventService, times(1)).getEventsByLocation(location);
    }

    @Test
    void testGetEventById() {
        Long eventId = 1L;
        Event event = new Event();
        when(eventService.getEventById(eventId)).thenReturn(event);

        ResponseEntity<Event> response = eventController.getEventById(eventId);
        HttpStatus statusCode = (HttpStatus) response.getStatusCode();
        Event retrievedEvent = response.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(event, retrievedEvent);
        verify(eventService, times(1)).getEventById(eventId);
    }

    // Add more test methods for other controller endpoints...

}
//

