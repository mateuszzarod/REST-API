package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

//makes bean during building context
//Klient połączeń
//Klasa, które jest odpowiedzialna za udostępnianie połączeń
// i wykonywania operacji na zewnętrznym API

@Component
public class TrelloClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;

    // implementacja wysłania żądania do API Trello

    public List<TrelloBoardDto> getTrelloBoards() {
        URI url = UriComponentsBuilder.fromHttpUrl(getHttpUrl())
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build().encode().toUri();

        try {
            TrelloBoardDto[] boardResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            return Optional.ofNullable(boardResponse)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList());
            //return Arrays.asList(ofNullable(boardResponse).orElse(new TrelloBoardDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
           return new ArrayList<>();
        }
    }

    private String getHttpUrl() {
        return trelloConfig.getTrelloApiEndpoint()
                + "/members/"
                + trelloConfig.getTrelloUsername()
                + "/boards";
    }

    //typ CreatedTrelloCard
    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build().encode().toUri();
        return
                //wysłanie żądania typu POST RestTemplate.postForObject()
                //request jest null, bo Trello narzuca nam wysyłanie parametrów przy pomocy query params
                //trzeci argument to klasa CreatedTrelloCard na którą zostanie zmapowana odpowiedź serwera
                restTemplate.postForObject(url, null, CreatedTrelloCard.class);
    }
}
