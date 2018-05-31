package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

//-> JSON
//tworzy endpointy
@RestController

@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET,
            value = "getTrelloBoards")

    public void getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
            System.out.println("This board contains lists: ");

            trelloBoardDto.getLists().forEach(trelloList ->
                    System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
        });
    }

    @RequestMapping(method = RequestMethod.GET,
    value = "getTrelloBoardsFilter")
    public List<TrelloBoardDto> getTrelloBoardsFilter() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());
        });

        return trelloBoards.stream()
                .filter(trelloBoardDto -> trelloBoardDto.getName() != null && trelloBoardDto.getId() != null)
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .collect(Collectors.toList());
    }

    public void getTrelloBadges(){
        CreatedTrelloCard createdTrelloCard = trelloClient.createNewCard(new TrelloCardDto("111ooo", "222aaA", "top", "asd",
                new TrelloBadges(1, new AttachmentByType(
                        new Trello(1,1)
                )
                )
        ));
        System.out.println(createdTrelloCard.getTrelloBadges().getVotes());
        System.out.println(createdTrelloCard.getTrelloBadges().getAttachments().getTrello().getBoard());
    }

    //przjmuje żądanie typu post
    //korzysta z ciała żądania jako źródła danych i wywołuje metodę klient
    @RequestMapping(method = RequestMethod.POST,
            value = "createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto){
        return trelloClient.createNewCard(trelloCardDto);
    }

}

