package com.crud.tasks.trello.client;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.config.TrelloConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TrelloClientTest {

    @InjectMocks
    private TrelloClient trelloClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private TrelloConfig trelloConfig;

    @Before
    public void init(){
        when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com");
        when(trelloConfig.getTrelloAppKey()).thenReturn("test");
        when(trelloConfig.getTrelloToken()).thenReturn("test");
        when(trelloConfig.getTrelloUsername()).thenReturn("m4tim4ti");
    }

    // TODO: 06.06.2018  //zrobić test na testowanie URI

    @Test
    public void shoudCreateCard() throws URISyntaxException {

        //Given
        //server response
        TrelloCardDto    trelloCardDto = new TrelloCardDto(
                "test Task",
                "Test Description",
                "top",
                "test_id",
                new TrelloBadges(1, new AttachmentsByType(new Trello(1,2)))
        );

        URI uri = new URI("http://test.com/cards?key=test&token=test&name=test%20Task&desc=Test%20Description&pos=top&idList=test_id");

        Trello trello = new Trello(1,1);
        AttachmentsByType attachmentsByType = new AttachmentsByType(trello);
        TrelloBadges trelloBadges = new TrelloBadges(1, attachmentsByType);

        CreatedTrelloCard createdTrelloCard = new CreatedTrelloCard(
                "1",
                "Test task",
                "http://test.com",
                trelloBadges
        );

        //request np. POST czyli np. BODY
        when(restTemplate.postForObject(any(URI.class), isNull(), any())).thenReturn(createdTrelloCard);

        //When
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        //Then
        assertEquals("1", newCard.getId());
        assertEquals("Test task", newCard.getName());
        assertEquals("http://test.com", newCard.getShortUrl());
    }

    @Test
    public void shouldFetchTrelloBoards() throws URISyntaxException {
        //Given
        //for restTemplate mock
        TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
        trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

        URI uri = new URI("http://test.com/members/m4tim4ti/boards?key=test&token=test&fields=name,id&lists=all");

        when(restTemplate.getForObject(uri, TrelloBoardDto[].class))
                .thenReturn(trelloBoards);

        //When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("test_board", fetchedTrelloBoards.get(0).getId());
        assertEquals("test_id", fetchedTrelloBoards.get(0).getName());
        assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
    }

    @Test
    public void shouldReturnEmptyList() throws URISyntaxException {
        //Given
        URI uri = new URI("http://test.com/members/" + trelloConfig.getTrelloUsername() +  "/boards?key=test&token=test&fields=name,id&lists=all");
        when(restTemplate.getForObject(uri, TrelloBoardDto.class)).thenReturn(null);

        //When
        List<TrelloBoardDto> isEmptyTrelloBoards = trelloClient.getTrelloBoards();

        //Then
        assertEquals(0, isEmptyTrelloBoards.size());
    }
}
