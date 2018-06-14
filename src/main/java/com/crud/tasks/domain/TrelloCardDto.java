package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//to jest obiekt, który będzie przechowywał
// dane niezbędne do dodania zadania w Trello (obiektt domenowy
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloCardDto {

    private String name;
    private String description;
    private String pos;
    private String listId;
    private TrelloBadges badges;
}
