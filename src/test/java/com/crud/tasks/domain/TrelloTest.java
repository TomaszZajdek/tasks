package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TrelloTest {
    Trello trello = new Trello(1,1);

    @Test
    void getBoardTest() {
        //given & when
        int boardNumber = trello.getBoard();
        //then
        assertEquals(1, boardNumber);
    }

    @Test
    void getCardTest() {
        //given & when
        int cardNumber = trello.getCard();
        //then
        assertEquals(1, cardNumber);
    }

}