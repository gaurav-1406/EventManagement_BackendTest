package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParticipantTest {

    @Test
    void participantPropertiesTest() {

        Participant participant = new Participant("John", "Doe", "Example Org", "Manager", "john.doe@example.com");


        assertEquals("John", participant.getFirstName());
        assertEquals("Doe", participant.getLastName());
        assertEquals("Example Org", participant.getOrganizationName());
        assertEquals("Manager", participant.getDesignation());
        assertEquals("john.doe@example.com", participant.getEmailAddress());
    }

    @Test
    void eventAssociationTest() {

        Event event = new Event();


        Participant participant = new Participant();
        

        participant.setEvent(event);


        assertEquals(event, participant.getEvent());
    }
}
