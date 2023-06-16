package com.example.demo.model;

import com.example.demo.model.Event;
import com.example.demo.model.Participant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class EventTest {

    private Event event;

    @BeforeEach
    void setUp() {
        event = new Event();
    }

    @Test
    void getIdTest() {
        int id = 1;
        event.setId(id);

        assertEquals(id, event.getId());
    }

    @Test
    void getNameTest() {
        String name = "Event name";
        event.setName(name);

        assertEquals(name, event.getName());
    }

    @Test
    void getDescriptionTest() {
        String description = "Event description";
        event.setDescription(description);

        assertEquals(description, event.getDescription());
    }

    // Write similar tests for the remaining getters and setters

    @Test
    void getParticipantsTest() {
        List<Participant> participants = new ArrayList<>();
        Participant participant1 = new Participant();
        Participant participant2 = new Participant();
        participants.add(participant1);
        participants.add(participant2);
        event.setParticipants(participants);

        List<Participant> result = event.getParticipants();

        assertNotNull(result);
        assertEquals(participants.size(), result.size());
        assertEquals(participants.get(0), result.get(0));
        assertEquals(participants.get(1), result.get(1));
    }

//    @Test
//    void registerParticipantTest() {
//        Participant participant = new Participant();
//
//        event.registerParticipant(participant);
//
//        List<Participant> participants = event.getParticipants();
//        assertNotNull(participants);
//        assertEquals(1, participants.size());
//        assertEquals(participant, participants.get(0));
//        assertEquals(event, participant.getEvent());
//    }

    @Test
    void registerParticipantTest() {
        Participant participant = new Participant();
        event.setParticipants(new ArrayList<>()); // Initialize the participants list

        event.registerParticipant(participant);

        List<Participant> participants = event.getParticipants();
        assertNotNull(participants);
        assertEquals(1, participants.size());
        assertEquals(participant, participants.get(0));
        assertEquals(event, participant.getEvent());
    }



}
