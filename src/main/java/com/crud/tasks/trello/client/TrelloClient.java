package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.Trello;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static java.util.Optional.ofNullable;

//makes bean during building context
//Klient połączeń
//Klasa, które jest odpowiedzialna za udostępnianie połączeń
// i wykonywania operacji na zewnętrznym API

@Component
public class TrelloClient {


    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.api.key}")
    private String trelloAppKey;
    @Value("${trello.api.token}")
    private String trelloToken;
    @Value("${trello.api.username}")
    private String trelloUsername;

    @Autowired
    private RestTemplate restTemplate;

    // implementacja wysłania żądania do API Trello

    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = UriComponentsBuilder.fromHttpUrl(getHttpUrl())
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();

        try {
            TrelloBoardDto[] boardResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            return Optional.ofNullable(boardResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
           return new ArrayList<>();
        }
    }

    private String getHttpUrl() {
        return trelloApiEndpoint
                + "/members/"
                + trelloUsername
                + "/boards";
    }



    //typ CreatedTrelloCard
    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/cards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .queryParam("badge", trelloCardDto.getBadges())
                .build().encode().toUri();
        return
                //wysłanie żądania typu POST RestTemplate.postForObject()
                //request jest null, bo Trello narzuca nam wysyłanie parametrów przy pomocy query params
                //trzeci argument to klasa CreatedTrelloCard na którą zostanie zmapowana odpowiedź serwera
                restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }


}
