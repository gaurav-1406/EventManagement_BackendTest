//package com.example.demo.dao;
//import com.example.demo.dao.EventRepository;
//import com.example.demo.model.Event;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class EventRepositoryTest {
//	@Mock
//    private EventRepository eventRepository;
//
//    @InjectMocks
//    private EventRepository eventRepositoryImpl;
//
//    @Test
//    void findByLocationTest() {
//        String location = "New York";
//        Event event1 = new Event();
//        Event event2 = new Event();
//        List<Event> events = Arrays.asList(event1, event2);
//        when(eventRepository.findByLocation(location)).thenReturn(events);
//
//        List<Event> result = eventRepositoryImpl.findByLocation(location);
//
//        assertEquals(events, result);
//        verify(eventRepository, times(1)).findByLocation(location);
//    }
//
//
//}
//
//

package com.example.demo.dao;


import com.example.demo.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class EventRepositoryTest {
    @Mock
    private EventRepository eventRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByLocationTest() {
        String location = "New York";
        Event event1 = new Event();
        Event event2 = new Event();
        List<Event> events = Arrays.asList(event1, event2);
        when(eventRepository.findByLocation(location)).thenReturn(events);

        List<Event> result = eventRepository.findByLocation(location);

        assertEquals(events, result);
        verify(eventRepository, times(1)).findByLocation(location);
    }
}


