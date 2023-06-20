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

    @Test
    public void testUpdateEvent() {
        Long eventId = 1L;
        Event eventDetails = new Event();
        Event updatedEvent = new Event();
        when(eventService.updateEvent(eventId, eventDetails)).thenReturn(updatedEvent);
        ResponseEntity<Event> response = eventController.updateEvent(eventId, eventDetails);
        assertEquals(updatedEvent, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteEvent() {
        Long eventId = 1L;
        ResponseEntity<Map<String, Boolean>> expectedResponse = ResponseEntity.ok(Map.of("deleted", true));
        doNothing().when(eventService).deleteEvent(eventId);
        ResponseEntity<Map<String, Boolean>> response = eventController.deleteEvent(eventId);
        assertEquals(expectedResponse, response);
    }

    @Test
    public void testRegisterParticipantForEvent_Success() {
        Long eventId = 1L;
        Participant participant = new Participant();
        boolean isRegistered = true;
        when(eventService.registerParticipantForEvent(eventId, participant)).thenReturn(isRegistered);
        ResponseEntity<String> response = eventController.registerParticipantForEvent(eventId, participant);
        assertEquals("Participant registered successfully.", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRegisterParticipantForEvent_NotFound() {
        Long eventId = 1L;
        Participant participant = new Participant();
        boolean isRegistered = false;
        when(eventService.registerParticipantForEvent(eventId, participant)).thenReturn(isRegistered);
        ResponseEntity<String> response = eventController.registerParticipantForEvent(eventId, participant);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }


}


