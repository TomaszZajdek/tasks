package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrelloMapperTest {

    private final TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void testMapToBoards() {
        // Given
        List<TrelloBoardDto> trelloBoardDtos = List.of(new TrelloBoardDto("1", "test_board", List.of()));

        // When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);

        // Then
        assertEquals(1, trelloBoards.size());
        assertEquals("test_board", trelloBoards.get(0).getName());
    }

    @Test
    void testMapToBoardsDto() {
        // Given
        List<TrelloBoard> trelloBoards = List.of(new TrelloBoard("1", "test_board", List.of()));

        // When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);

        // Then
        assertEquals(1, trelloBoardDtos.size());
        assertEquals("test_board", trelloBoardDtos.get(0).getName());
    }

    @Test
    void testMapToList() {
        // Given
        List<TrelloListDto> trelloListDtos = List.of(new TrelloListDto("1", "test_list", false));

        // When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        // Then
        assertEquals(1, trelloLists.size());
        assertEquals("test_list", trelloLists.get(0).getName());
    }

    @Test
    void testMapToListDto() {
        // Given
        List<TrelloList> trelloLists = List.of(new TrelloList("1", "test_list", false));

        // When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        // Then
        assertEquals(1, trelloListDtos.size());
        assertEquals("test_list", trelloListDtos.get(0).getName());
    }

    @Test
    void testMapToCardDto() {
        // Given
        TrelloCard trelloCard = new TrelloCard("test_card", "test_description", "top", "1");

        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        // Then
        assertEquals("test_card", trelloCardDto.getName());
        assertEquals("test_description", trelloCardDto.getDescription());
    }

    @Test
    void testMapToCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_card", "test_description", "top", "1");

        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        // Then
        assertEquals("test_card", trelloCard.getName());
        assertEquals("test_description", trelloCard.getDescription());
    }
}