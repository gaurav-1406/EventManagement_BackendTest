
package com.example.demo.service;

import com.example.demo.dao.ParticipantRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Participant;
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

    @Test
    public void testUpdateParticipant() {
        Long id = 1L;
        Participant existingParticipant = new Participant();
        Participant updatedParticipant = new Participant();
        when(participantRepository.findById(id)).thenReturn(Optional.of(existingParticipant));
        when(participantRepository.save(existingParticipant)).thenReturn(updatedParticipant);

        Participant result = participantService.updateParticipant(id, existingParticipant);

        assertNotNull(result);
        assertEquals(updatedParticipant, result);
        verify(participantRepository, times(1)).findById(id);
        verify(participantRepository, times(1)).save(existingParticipant);
    }

    @Test
    public void testUpdateParticipant_NotFound() {
        Long id = 1L;
        Participant participant = new Participant();
        when(participantRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> participantService.updateParticipant(id, participant));
        verify(participantRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteParticipant() {
        Long id = 1L;
        Participant participant = new Participant();
        when(participantRepository.findById(id)).thenReturn(Optional.of(participant));

        participantService.deleteParticipant(id);

        verify(participantRepository, times(1)).findById(id);
        verify(participantRepository, times(1)).delete(participant);
    }

    @Test
    public void testDeleteParticipant_NotFound() {
        Long id = 1L;
        when(participantRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> participantService.deleteParticipant(id));
        verify(participantRepository, times(1)).findById(id);
        verify(participantRepository, times(0)).delete(any(Participant.class));
    }
}




