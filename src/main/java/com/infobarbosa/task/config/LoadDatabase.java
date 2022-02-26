package com.infobarbosa.task.config;

import com.infobarbosa.task.model.Task;
import com.infobarbosa.task.repository.TaskRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabaseC(TaskRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Task("Passear o cachorro")));
      log.info("Preloading " + repository.save(new Task("Estudar para a prova")));
    };
  }
}