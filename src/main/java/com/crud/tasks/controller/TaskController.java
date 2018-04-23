package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/v1/task")
public class TaskController {
    @Autowired
    private DbService service;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "getTasks")
    public List<TaskDto> getTasks(){
    return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "getTaskList")
    public List<TaskDto> getTaskList(){
        return new ArrayList<>();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            value = "getTaskById"
    )
    public TaskDto getTask(Long id){
        return taskMapper.mapToTaskDto(service.findOne(id));
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
            value = "createTask",
            consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto) {
        service.saveTask(taskMapper.mapToTask(taskDto));
    }

}
