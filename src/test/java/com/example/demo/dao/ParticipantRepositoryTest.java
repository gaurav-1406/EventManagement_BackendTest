//package com.example.demo.dao;
//
//import com.example.demo.dao.ParticipantRepository;
//import com.example.demo.model.Participant;
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
//class ParticipantRepositoryTest {
//
//    @Mock
//    private ParticipantRepository participantRepository;
//
//    @InjectMocks
//    private ParticipantRepository participantRepositoryImpl;
//
//    @Test
//    void findAllTest() {
//        Participant participant1 = new Participant();
//        Participant participant2 = new Participant();
//        List<Participant> participants = Arrays.asList(participant1, participant2);
//        when(participantRepository.findAll()).thenReturn(participants);
//
//        List<Participant> result = participantRepositoryImpl.findAll();
//
//        assertEquals(participants, result);
//        verify(participantRepository, times(1)).findAll();
//    }
//
//
//}

package com.example.demo.dao;


import com.example.demo.model.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ParticipantRepositoryTest {

    @Mock
    private ParticipantRepository participantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAllTest() {
        Participant participant1 = new Participant();
        Participant participant2 = new Participant();
        List<Participant> participants = Arrays.asList(participant1, participant2);
        when(participantRepository.findAll()).thenReturn(participants);

        List<Participant> result = participantRepository.findAll();

        assertEquals(participants, result);
        verify(participantRepository, times(1)).findAll();
    }
}

