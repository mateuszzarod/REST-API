package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//klasa na którą mapujemy odpowiedź serwera aplikacji
//finalna reprezentacją danych na temat utworzonego zadania
//to co nam przyśle serwer mapujemy na wynik i za pomocą Jsona osbługujemy tylko interesujące nas pola
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class CreatedTrelloCard {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("shortUrl")
    private String shortUrl;

    @JsonProperty("badges")
    private TrelloBadges trelloBadges;
}
