package com.crud.tasks.trello.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter

public class TrelloConfig {
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.api.key}")
    private String trelloAppKey;
    @Value("${trello.api.token}")
    private String trelloToken;
    @Value("${trello.api.username}")
    private String trelloUsername;
}
