package com.crud.tasks.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BadgesTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void shouldSerializeToJson() throws Exception {
        // Given
        Trello trello = new Trello(5, 15);
        AttachmentsByType attachmentsByType = new AttachmentsByType(trello);
        Badges badges = new Badges(10, attachmentsByType);

        // When
        String jsonResult = objectMapper.writeValueAsString(badges);

        // Then
        assertEquals("{\"votes\":10,\"attachmentsByType\":{\"trello\":{\"board\":5,\"card\":15}}}", jsonResult);
    }
}