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
import java.util.Optional;
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
                //.filter(trelloBoardDto -> trelloBoardDto.getName() != null && trelloBoardDto.getId() != null)
                .filter(trelloBoardDto -> Optional.ofNullable(trelloBoardDto.getName()).orElse("Epty Board Name"))
                .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
                .collect(Collectors.toList())

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

/*
    @RequestMapping(method = RequestMethod.POST,
    value = "createNewCard")
*/

/*    public List<TrelloBoardDto> getTrelloBoards() {

        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        return trelloClient.getTrelloBoards().stream()
                .filter(t -> t.getName().contains("Kodilla"))
                .filter(trelloBoardDto -> trelloBoardDto.getName() != null && trelloBoardDto.getId() != null)
                .collect(Collectors.toList());
    }*/


}


        /*(Wyzwanie - Trudne zadanie)

        Przebuduj warunek decydujący o tym co należy zwrócić w
        metodzie getTrelloBoards, wykorzystując klasę Optional, o której wspominaliśmy w
        module 15.
        https://github.com/mateuszzarod/REST-API/commit/f3ac8ae54ff18788a4d7e9e35d61cdd36f24da79*/

        //http://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html
        //https://codecouple.pl/2016/03/09/pozbadz-sie-null-pointerow-java-util-optional//*
        /*trelloBoards.forEach(trelloBoardDto -> System.out.println(
                trelloBoardDto.getId()
                        + " "
                        + trelloBoardDto.getName()));
        return trelloBoards;*/

