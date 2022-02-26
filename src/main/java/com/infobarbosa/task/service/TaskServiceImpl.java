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

    public List<Task> getAll(){
        return (List<Task>) taskRepository.findAll();
    }

    public Optional<Task> get(Long id){
        return taskRepository.findById( id );
    }

    public Task create(Task task) {
        Task created = taskRepository.save( task );
        logger.debug( created.toString() );
        return created;
    }

    public Task update(Task task, Long id){
        logger.debug( "Atualizando task: " + task.toString() );

        return taskRepository.findById(id)
            .map(t -> {
                t.setDescription(task.getDescription());
                return taskRepository.save( task );
            })
            .orElseGet(() -> {
                task.setId(id);
                return taskRepository.save( task );
            });
    }

    public void delete( Long id ){
        taskRepository.deleteById(id);
    }
}
