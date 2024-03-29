package com.crud.tasks.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadgesTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldDeserializeFromJson() throws Exception {
        // Given
        String json = "{\"votes\":10,\"attachmentsByType\":{\"trello\":{\"board\":5,\"card\":3}}}";

        // When
        Badges badges = objectMapper.readValue(json, Badges.class);

        // Then
        assertEquals(10, badges.getVotes());
        assertEquals(5, badges.getAttachmentsByType().getTrello().getBoard());
        assertEquals(3, badges.getAttachmentsByType().getTrello().getCard());
    }
}