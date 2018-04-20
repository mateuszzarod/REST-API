package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @RequestMapping(
            method = RequestMethod.GET,
            value = "getTasks")
    public List<TaskDto> getTasks(){
        return new ArrayList<>();
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "getTask")
    public TaskDto getTask(String taskID){
        return new TaskDto((long)1, "test title", "test content" );
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "deleteTask")
    public void deleteTask(String taskDto){
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            value = "updateTask")
    public TaskDto updateTask(TaskDto taskDto){
        return new TaskDto((long)1, "Edited test title", "Edited test content");
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "createTask")
    public void createTask(@RequestBody TaskDto taskDto){
        System.out.println("Hello");
    }
}
