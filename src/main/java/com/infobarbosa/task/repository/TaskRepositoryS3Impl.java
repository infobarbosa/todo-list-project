package com.infobarbosa.task.repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infobarbosa.task.model.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;


@Repository
public class TaskRepositoryS3Impl implements TaskRepository{
    private final Logger logger = LoggerFactory.getLogger(TaskRepositoryS3Impl.class);

    @Autowired
    AmazonS3 s3;

    @Value("${bucketName}")
    private String bucketName;

    public void save(Task task){

        createBucket(bucketName);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] bytesToWrite = objectMapper.writeValueAsBytes(task);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(bytesToWrite.length);

            logger.debug("Hora de fazer o putObject. Oremos!");
            s3.putObject(
                new PutObjectRequest(
                    bucketName, //bucket name
                    task.getId().toString() + ".json", //key
                    new ByteArrayInputStream(bytesToWrite),
                    objectMetadata)    
            );
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    };
    
    public void delete(UUID id){
        logger.info("Deletando o objeto " + id);
        s3.deleteObject(
            new DeleteObjectRequest(bucketName, id + ".json")
        ); 
        logger.info("Objeto " + id + " deletado.");
    }

    public Optional<Task> find(UUID id){
        return find(id + ".json");
    }

    public Optional<Task> find(String key){
        Task task = null;

        try{
            S3Object o = s3.getObject( 
                new GetObjectRequest( 
                    new S3ObjectId(bucketName, key)
                ) 
            );
    
            S3ObjectInputStream stream = o.getObjectContent();
            try {
                task = new ObjectMapper().readValue(stream, Task.class);
                return Optional.of(task);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    
        }catch(AmazonS3Exception e){
            logger.error( "Problema buscando objeto id " + key + ". Error code " + e.getErrorCode());
        }

        return Optional.ofNullable(task);
    }

    public List<Task> findAll(){
        List<Task> tasks = new ArrayList<>();
        logger.info("Metodo findAll de TaskRepositoryS3Impl chamado");
        ObjectListing list = s3.listObjects( bucketName );
        List<S3ObjectSummary> summaries = list.getObjectSummaries();
        summaries.stream().forEach(
                summary -> tasks.add(
                        find( summary.getKey() ).get()
                )
        );

        return tasks;
    }

    private void createBucket(String bucketName){
        logger.info("createBucket chamado! " + bucketName);

        if( !s3.doesBucketExistV2(bucketName) ){
            logger.info("criando bucket " + bucketName);
            s3.createBucket(bucketName);
        }
    }
}