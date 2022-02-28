package com.infobarbosa.task.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.infobarbosa.task.model.Task;

import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository{
    public void save(Task task);
    public void delete(UUID id);    

    public Optional<Task> find(UUID id);
    public List<Task> findAll();
}