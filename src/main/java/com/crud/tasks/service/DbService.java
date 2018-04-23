package com.crud.tasks.service;


import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//odpowiada za komunikację z bazą danych
//wstrzykuje do siebie TaskRepository
//inversion control

@Service
public class DbService {
    @Autowired
    private TaskRepository taskRepository;

    //opakowanie dla metody TaskRepository
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    //zadanie 15.2
    public Task findTaskById(final Long id) {
        return taskRepository.getTaskById(id);
    }

    public Task saveTask(final Task task){
        return taskRepository.save(task);
    }



}
