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


	}
