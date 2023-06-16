package com.example.demo.controller;

import com.example.demo.model.Participant;
import com.example.demo.service.ParticipantService;
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

class ParticipantControllerTest {
    @Mock
    private ParticipantService participantService;

    @InjectMocks
    private ParticipantController participantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateParticipant() {
        Participant participant = new Participant();
        when(participantService.createParticipant(participant)).thenReturn(participant);

        Participant createdParticipant = participantController.createParticipant(participant);

        assertEquals(participant, createdParticipant);
        verify(participantService, times(1)).createParticipant(participant);
    }

    @Test
    void testGetAllParticipants() {
        List<Participant> participants = new ArrayList<>();
        when(participantService.getAllParticipants()).thenReturn(participants);

        List<Participant> retrievedParticipants = participantController.getAllParticipants();

        assertEquals(participants, retrievedParticipants);
        verify(participantService, times(1)).getAllParticipants();
    }

    @Test
    void testGetParticipantById() {
        Long participantId = 1L;
        Participant participant = new Participant();
        when(participantService.getParticipantById(participantId)).thenReturn(participant);

        ResponseEntity<Participant> response = participantController.getParticipantById(participantId);
        HttpStatus statusCode = (HttpStatus) response.getStatusCode();
        Participant retrievedParticipant = response.getBody();

        assertEquals(HttpStatus.OK, statusCode);
        assertEquals(participant, retrievedParticipant);
        verify(participantService, times(1)).getParticipantById(participantId);
    }

    // Add more test methods for other controller endpoints...

}
