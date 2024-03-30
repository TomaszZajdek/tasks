package com.crud.tasks.trello.Validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class ValidatorTest {

    @Autowired
    TrelloValidator validator;

    @Test
    void validateTrelloBoardsTest() {
        //given
        TrelloBoard firstBoard = new TrelloBoard("WER1R", "name", new ArrayList<>());
        TrelloBoard secondBoard = new TrelloBoard("QWERTY", "test", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(firstBoard);
        trelloBoards.add(secondBoard);
        //when
        List<TrelloBoard> validatedList = validator.validateTrelloBoards(trelloBoards);
        //then
        assertFalse(validatedList.isEmpty());
        assertEquals(1,validatedList.size());
        assertEquals(firstBoard,validatedList.get(0));
        assertEquals("WER1R",validatedList.get(0).getId());
        assertEquals("name",validatedList.get(0).getName());
    }

}