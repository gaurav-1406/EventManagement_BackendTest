package com.example.demo.model;

import org.junit.jupiter.api.Test;

import com.example.demo.model.Event;
import com.example.demo.model.Participant;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    @Test
    void participantPropertiesTest() {
        // Create a sample participant
        Participant participant = new Participant("John", "Doe", "Example Org", "Manager", "john.doe@example.com");

        // Verify participant properties
        assertEquals("John", participant.getFirstName());
        assertEquals("Doe", participant.getLastName());
        assertEquals("Example Org", participant.getOrganizationName());
        assertEquals("Manager", participant.getDesignation());
        assertEquals("john.doe@example.com", participant.getEmailAddress());
    }

    @Test
    void eventAssociationTest() {
        // Create a sample event
        Event event = new Event();

        // Create a sample participant
        Participant participant = new Participant();
        
        // Associate the participant with the event
        participant.setEvent(event);

        // Verify event association
        assertEquals(event, participant.getEvent());
    }
}
