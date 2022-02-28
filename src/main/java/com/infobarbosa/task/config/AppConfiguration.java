package com.infobarbosa.task.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(AppConfiguration.class);

  @Value("${s3Endpoint}")
  private String endpoint;

  @Bean
  public AmazonS3 getAmazonS3(){

    logger.info("criando cliente S3 com o endpoint " + endpoint);
    AmazonS3 s3 = AmazonS3ClientBuilder
              .standard()
              .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, "sa-east-1")) 
              .withCredentials(new DefaultAWSCredentialsProviderChain())
              .withPathStyleAccessEnabled(true)
              .build();

    logger.info("Bean S3 client criado com sucesso!");
    return s3;
  }
}