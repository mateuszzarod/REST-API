package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

//-> JSON
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {

        return trelloClient.getTrelloBoards().stream()
                .filter(t -> t.getName().contains("Kodilla"))
                .filter(trelloBoardDto -> trelloBoardDto.getName() != null && trelloBoardDto.getId() != null)
                .collect(Collectors.toList());
    }
}



        /*(Wyzwanie - Trudne zadanie) Przebuduj warunek decydujący o tym co należy zwrócić w
        metodzie getTrelloBoards, wykorzystując klasę Optional, o której wspominaliśmy w
        module 15.*/
        //http://www.oracle.com/technetwork/articles/java/java8-optional-2175753.html
        //https://codecouple.pl/2016/03/09/pozbadz-sie-null-pointerow-java-util-optional/
        /*
        trelloBoards.forEach(trelloBoardDto -> System.out.println(
                trelloBoardDto.getId()
                        + " "
                        + trelloBoardDto.getName()));
        return trelloBoards;
*/

