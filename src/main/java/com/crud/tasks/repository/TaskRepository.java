package com.crud.tasks.repository;

import com.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    @Override
    Task save(Task task);

    //zadanie 15.2
    Task getTaskById(Long id);

    Optional<Task> findById(Long id);

    //czy nadpisanie tej metody jest potrzebne?
 /*   @Override
    void delete (Long id);*/

}



