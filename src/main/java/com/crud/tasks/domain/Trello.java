package com.crud.tasks.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trello {
    @JsonProperty
    int board;
    @JsonProperty
    int card;

    @Override
    public String toString() {
        return "Trello{" +
                "board=" + board +
                ", card=" + card +
                '}';
    }
}
