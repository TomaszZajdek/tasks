package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TrelloConfigTest {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    void givenTrelloData() {
        //Given & When
        URI url = UriComponentsBuilder
                .fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build()
                .encode()
                .toUri();

        URI url2 = UriComponentsBuilder
                .fromHttpUrl("https://api.trello.com/1" + "/members/" + "6596acd5a59c36f7b6f07248" + "/boards")
                .queryParam("key", "28cc7885e2c78e75262738e9364c94aa")
                .queryParam("token", "ATTA276abab6eb8aac3ced0150e2baeecd31ede157aab62edf96047548ff49039234C52FAF48")
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build()
                .encode()
                .toUri();

        //Then
        assertEquals(url, url2);
    }
}