package com.crud.tasks.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreatedTrelloCardDtoTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldSerializeToJson() throws Exception {
        // Given
        CreatedTrelloCardDto cardDto = new CreatedTrelloCardDto("1", "Test Card", "http://test.url");

        // When
        String jsonResult = objectMapper.writeValueAsString(cardDto);

        // Then
        assertThat(jsonResult).contains("\"id\":\"1\"");
        assertThat(jsonResult).contains("\"name\":\"Test Card\"");
        assertThat(jsonResult).contains("\"shortUrl\":\"http://test.url\"");
    }

    @Test
    void shouldDeserializeFromJson() throws Exception {
        // Given
        String json = "{\"id\":\"1\",\"name\":\"Test Card\",\"shortUrl\":\"http://test.url\"}";

        // When
        CreatedTrelloCardDto cardDto = objectMapper.readValue(json, CreatedTrelloCardDto.class);

        // Then
        assertThat(cardDto.getId()).isEqualTo("1");
        assertThat(cardDto.getName()).isEqualTo("Test Card");
        assertThat(cardDto.getShortUrl()).isEqualTo("http://test.url");
    }
}