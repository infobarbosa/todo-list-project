package com.infobarbosa.task.service;

import java.util.List;
import java.util.Optional;

import com.infobarbosa.task.model.Task;

public interface TaskService {
    

    public Task create(Task task);
    public Task update(Task task, Long id);

    public Optional<Task> get(Long id);
    public List<Task> getAll();
    public void delete(Long id);
}
