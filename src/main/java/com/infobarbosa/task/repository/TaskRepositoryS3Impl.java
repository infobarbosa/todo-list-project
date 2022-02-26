package com.infobarbosa.task.repository;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infobarbosa.task.model.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepositoryS3Impl implements TaskRepository{

    @Autowired
    AmazonS3 s3;

    public void save(Task task){

        createBucket(task.getId().toString());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            byte[] bytesToWrite = objectMapper.writeValueAsBytes(task);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(bytesToWrite.length);
            s3.putObject(
                new PutObjectRequest(
                    task.getId().toString(), //bucket name
                    task.getId().toString() + ".json", //key
                    new ByteArrayInputStream(bytesToWrite),
                    objectMetadata)    
            );
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    };
    
    public void delete(Long id){ /*TODO*/ };

    public Optional<Task> find(Long id){/*TODO*/ return null;};
    public List<Task> findAll(){/*TODO*/ return null;};

    private void createBucket(String bucketName){
        if( !s3.doesBucketExistV2(bucketName)){
            s3.createBucket(bucketName);
        }
    }
}