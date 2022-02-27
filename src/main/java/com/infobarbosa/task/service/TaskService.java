package com.infobarbosa.task.service;

import java.util.List;
import java.util.Optional;

import com.infobarbosa.task.model.Task;

import org.springframework.stereotype.Service;

@Service
public interface TaskService {
    
    public void save(Task task);

    public Optional<Task> find(Long id);
    public List<Task> findAll();
    public void delete(Long id);
}
