package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
//spring utworzy controller z niezbędnym ResponseBody
//Odpowiedź controlera jest w formacie JSON
//wejście do aplikacji z zewnętrznego świata
@RequestMapping("/v1/task")
//generowanie adresu dla naszego API
//Będziemy korzystali z niej w dwóch miejscach - na
//szczycie Controllera, aby określić adres pod którym dany controller będzie przyjmował żądania oraz
// nad metodami, aby określić adresy i typy żądań na które metody Controllera będą reagować.
public class TaskController {

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    //method - poinformowanie Springa jakiego rodzaju żądania ma oczekiwać pod podanym adresem, aby wszystkie inne
    //żądania były odrzucane.
    public List<TaskDto> getTasks(){
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getTask")
    public TaskDto getTask(String taskID){
        return new TaskDto((long)1, "test title", "test content" );
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteTask")
    public void deleteTask(String taskDto){

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(TaskDto taskDto){
        return new TaskDto((long)1, "Edited test title", "Edited test content");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(TaskDto taskDto){

    }
}

