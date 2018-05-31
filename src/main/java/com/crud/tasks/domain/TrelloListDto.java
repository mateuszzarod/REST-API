package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


//mapuje na obiekt, zwracany przez przez żadanie klienta
//IgnoreProperties Mówi aplikacji, aby nie zwracać uwagi na parametry zawarte
// w odpowiedzi JSON, jeżeli nie znajdują się w danej klasie jako pola
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloListDto {
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("closed")
    private boolean isClosed;

}
