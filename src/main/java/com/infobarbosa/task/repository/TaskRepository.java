package com.infobarbosa.task.repository;

import com.infobarbosa.task.model.Task;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{
    
}