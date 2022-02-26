package com.infobarbosa.task.repository;

import java.util.List;
import java.util.Optional;

import com.infobarbosa.task.model.Task;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository{
    public void save(Task task);
    public void delete(Long id);    

    public Optional<Task> find(Long id);
    public List<Task> findAll();
}