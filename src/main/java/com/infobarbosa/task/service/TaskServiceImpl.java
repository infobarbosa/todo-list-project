package com.infobarbosa.task.service;

import java.util.List;
import java.util.Optional;

import com.infobarbosa.task.model.Task;
import com.infobarbosa.task.repository.TaskRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService{
    private final Logger logger = LoggerFactory.getLogger( TaskServiceImpl.class.getName() );

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> findAll(){
        return (List<Task>) taskRepository.findAll();
    }

    public Optional<Task> find(Long id){
        return taskRepository.find( id );
    }

    public void save(Task task){
        logger.debug( "Salvando a task: " + task.toString() );

        taskRepository.save( task );

    }

    public void delete( Long id ){
        taskRepository.delete(id);
    }
}
