package com.teamV.imageUploadAWS.mediaConfig.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AmazonConfig {

    @Bean
    public AmazonS3 s3(){
        AWSCredentials credentials = new BasicAWSCredentials(
          "Access Key","Secret Key"
        );
    return AmazonS3ClientBuilder
            .standard()
            .withRegion("ap-south-1")
            .withCredentials( new AWSStaticCredentialsProvider( credentials ))
            .build();
    }

}
