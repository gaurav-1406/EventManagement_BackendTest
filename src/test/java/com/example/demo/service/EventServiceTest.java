package com.example.demo.service;

import com.example.demo.dao.EventRepository;
import com.example.demo.dao.ParticipantRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Event;
import com.example.demo.model.Participant;
import com.example.demo.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

	private EventService eventService;

	@Mock
	private EventRepository eventRepository;

	@Mock
	private ParticipantRepository participantRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		eventService = new EventService(eventRepository, participantRepository);
	}

	@Test
	void createEventTest() {
		Event event = new Event();
		when(eventRepository.save(any(Event.class))).thenReturn(event);

		Event result = eventService.createEvent(event);

		assertNotNull(result);
		assertEquals(event, result);
		verify(eventRepository, times(1)).save(event);
	}

	@Test
	void getAllEventsTest() {
		List<Event> events = new ArrayList<>();
		when(eventRepository.findAll()).thenReturn(events);

		List<Event> result = eventService.getAllEvents();

		assertNotNull(result);
		assertEquals(events, result);
		verify(eventRepository, times(1)).findAll();
	}

	@Test
	void getEventsByLocationTest() {
		String location = "New York";
		List<Event> events = new ArrayList<>();
		when(eventRepository.findByLocation(location)).thenReturn(events);

		List<Event> result = eventService.getEventsByLocation(location);

		assertNotNull(result);
		assertEquals(events, result);
		verify(eventRepository, times(1)).findByLocation(location);
	}

	@Test
	void getEventByIdTest() {
		Long id = 1L;
		Event event = new Event();
		when(eventRepository.findById(id)).thenReturn(Optional.of(event));

		Event result = eventService.getEventById(id);

		assertNotNull(result);
		assertEquals(event, result);
		verify(eventRepository, times(1)).findById(id);
	}

	@Test
	void getEventById_ThrowsResourceNotFoundExceptionTest() {
		Long id = 1L;
		when(eventRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> eventService.getEventById(id));
		verify(eventRepository, times(1)).findById(id);
	}

	@Test
	void testUpdateEvent() {
		long id = 1L;
		Event existingEvent = new Event();
		existingEvent.setId((int)id);
		existingEvent.setName("Old Name");
		existingEvent.setDescription("Old Description");

		Event updatedEvent = new Event();
		updatedEvent.setName("New Name");
		updatedEvent.setDescription("New Description");

		when(eventRepository.findById(id)).thenReturn(Optional.of(existingEvent));
		when(eventRepository.save(existingEvent)).thenReturn(existingEvent);
		Event result = eventService.updateEvent(id, updatedEvent);

		assertEquals(updatedEvent.getName(), result.getName());
		assertEquals(updatedEvent.getDescription(), result.getDescription());
		verify(eventRepository, times(1)).findById(id);
		verify(eventRepository, times(1)).save(existingEvent);
	}

	@Test
	void testUpdateEvent_WhenEventNotFound() {
		long id = 1L;
		Event updatedEvent = new Event();
		updatedEvent.setName("New Name");
		updatedEvent.setDescription("New Description");

		when(eventRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> eventService.updateEvent(id, updatedEvent));
		verify(eventRepository, times(1)).findById(id);
		verify(eventRepository, times(0)).save(any());
	}

	@Test
	void testDeleteEvent() {
		long id = 1L;
		Event event = new Event();
		event.setId((int)id);

		when(eventRepository.findById(id)).thenReturn(Optional.of(event));

		eventService.deleteEvent(id);

		verify(eventRepository, times(1)).findById(id);
		verify(eventRepository, times(1)).delete(event);
	}

	@Test
	void testDeleteEvent_WhenEventNotFound() {
		long id = 1L;

		when(eventRepository.findById(id)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> eventService.deleteEvent(id));
		verify(eventRepository, times(1)).findById(id);
		verify(eventRepository, times(0)).delete(any());
	}

	@Test
	void testGetEventParticipants() {
		long id = 1L;
		Event event = new Event();
		event.setId((int)id);

		List<Participant> participants = new ArrayList<>();
		participants.add(new Participant());
		participants.add(new Participant());

		event.setParticipants(participants);
		when(eventRepository.findById(id)).thenReturn(Optional.of(event));

		List<Participant> retrievedParticipants = eventService.getEventParticipants(id);

		assertEquals(participants, retrievedParticipants);
		verify(eventRepository, times(1)).findById(id);
	}


	// ExistingEventId
	@Test
	void deleteEvent_ExistingEventId_DeletesEvent() {
		Long eventId = 1L;
		Event event = new Event();
		when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

		eventService.deleteEvent(eventId);

		verify(eventRepository, times(1)).findById(eventId);
		verify(eventRepository, times(1)).delete(event);
	}

	// NonExistingEventId
	@Test
	void deleteEvent_NonExistingEventId_ThrowsResourceNotFoundException() {
		Long eventId = 1L;
		when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> eventService.deleteEvent(eventId));
		verify(eventRepository, times(1)).findById(eventId);
	}

	@Test
	void getEventParticipants_ExistingEventId_ReturnsParticipants() {
		Long eventId = 1L;
		Event event = new Event();
		List<Participant> participants = new ArrayList<>();
		event.setParticipants(participants);
		when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

		List<Participant> result = eventService.getEventParticipants(eventId);

		assertNotNull(result);
		assertEquals(participants, result);
		verify(eventRepository, times(1)).findById(eventId);
	}

	@Test
	void getEventParticipants_NonExistingEventId_ThrowsResourceNotFoundException() {
		Long eventId = 1L;
		when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> eventService.getEventParticipants(eventId));
		verify(eventRepository, times(1)).findById(eventId);
	}

	@Test
	void registerParticipant_ValidEventAndParticipant_RegistersParticipant() {
		Long eventId = 1L;
		Event event = new Event();
		Participant participant = new Participant();
		when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
		when(participantRepository.save(participant)).thenReturn(participant);

		Participant result = eventService.registerParticipant(eventId, participant);

		assertNotNull(result);
		assertEquals(event, result.getEvent());
		verify(eventRepository, times(1)).findById(eventId);
		verify(participantRepository, times(1)).save(participant);
	}


	@Test
	void registerParticipantForEvent_NonExistingEventId_ReturnsFalse() {
		Long eventId = 1L;
		when(eventRepository.findById(eventId)).thenReturn(Optional.empty());

		boolean result = eventService.registerParticipantForEvent(eventId, new Participant());

		assertFalse(result);
		verify(eventRepository, times(1)).findById(eventId);
		verify(eventRepository, never()).save(any(Event.class));
	}
}

