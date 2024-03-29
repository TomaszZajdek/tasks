package com.crud.tasks.trello.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {
    @Mock
    private TrelloClient trelloClient;
    @InjectMocks
    private TrelloService trelloService;
    @Mock
    private AdminConfig adminConfig;
    @Mock
    private SimpleEmailService emailService;

    @Test
    void shouldFetchTrelloBoards () {
        //Given
        List<TrelloListDto> trelloListDtoList = List.of(new TrelloListDto(
                "id",
                "name",
                false));
        List<TrelloBoardDto> trelloBoardDtoList = List.of(new TrelloBoardDto(
                "id",
                "name",
                trelloListDtoList));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtoList);
        //When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();
        //Then
        assertEquals(trelloBoardDtoList, result);
    }

    @Test
    void shouldCreateTrelloCard() {
        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test_card", "test_description", "top", "test_listId");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("test_id", "test_card", "test_url");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        // When
        CreatedTrelloCardDto newCard = trelloService.createTrelloCard(trelloCardDto);

        // Then
        assertEquals("test_card", newCard.getName());
        verify(emailService).send(any(Mail.class));
        verifyNoMoreInteractions(emailService);
    }
}