package com.infobarbosa.task.controller;

import java.util.List;
import java.util.Optional;

import com.infobarbosa.task.model.Task;
import com.infobarbosa.task.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {    
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getAll(){
        return taskService.getAll();
    }

    @GetMapping(value = "/tasks/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Task> get(@PathVariable Long id){
        return taskService.get( id );
    }

    @PostMapping(value = "/tasks")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Task create(@RequestBody Task customer){
        return taskService.create( customer );
    }

    @PutMapping(value = "/tasks/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Task update(@RequestBody Task customer, @PathVariable Long id){
        return taskService.update( customer, id );
    }

    @DeleteMapping(value = "/tasks/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        taskService.delete( id );
    }
}
