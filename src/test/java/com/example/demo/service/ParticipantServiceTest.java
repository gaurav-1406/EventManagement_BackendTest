package com.example.demo.service;

import com.example.demo.dao.ParticipantRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Participant;
import com.example.demo.service.ParticipantService;
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

class ParticipantServiceTest {

    private ParticipantService participantService;

    @Mock
    private ParticipantRepository participantRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        participantService = new ParticipantService(participantRepository);
    }

    @Test
    void createParticipantTest() {
        Participant participant = new Participant();
        when(participantRepository.save(any(Participant.class))).thenReturn(participant);

        Participant result = participantService.createParticipant(participant);

        assertNotNull(result);
        assertEquals(participant, result);
        verify(participantRepository, times(1)).save(participant);
    }

    @Test
    void getAllParticipantsTest() {
        List<Participant> participants = new ArrayList<>();
        when(participantRepository.findAll()).thenReturn(participants);

        List<Participant> result = participantService.getAllParticipants();

        assertNotNull(result);
        assertEquals(participants, result);
        verify(participantRepository, times(1)).findAll();
    }

    @Test
    void getParticipantByIdTest() {
        Long id = 1L;
        Participant participant = new Participant();
        when(participantRepository.findById(id)).thenReturn(Optional.of(participant));

        Participant result = participantService.getParticipantById(id);

        assertNotNull(result);
        assertEquals(participant, result);
        verify(participantRepository, times(1)).findById(id);
    }

    @Test
    void getParticipantById_ThrowsResourceNotFoundExceptionTest() {
        Long id = 1L;
        when(participantRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> participantService.getParticipantById(id));
        verify(participantRepository, times(1)).findById(id);
    }

    

}

