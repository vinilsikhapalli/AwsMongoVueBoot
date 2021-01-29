package com.teamV.imageUploadAWS.mediaConfig.fileStores;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

@Service
public class FileStore {


    private final AmazonS3 amazonS3;

    @Autowired
    public FileStore(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void saveFIle(String path, String fileName,
                         Optional<Map<String,String>> optionalMetaData, InputStream inputStream ) throws IllegalAccessException, IOException {

        ObjectMetadata metadata = new ObjectMetadata();

        byte[] bytes = IOUtils.toByteArray(inputStream);
        metadata.setContentLength(bytes.length);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        optionalMetaData.ifPresent( map -> {
         if(!map.isEmpty()){
                    map.forEach(metadata :: addUserMetadata);
                }
        });
        try{
            amazonS3.putObject(path,fileName,byteArrayInputStream,metadata);
            System.out.println("File uploaded successfully...**");

        }catch (AmazonServiceException exception){
            throw new IllegalAccessException("something went wrong please try again..."+exception);
        }
    }

    public byte[] imageDownload(String path, String key) {
        try {
            S3Object object = amazonS3.getObject(path, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
           return IOUtils.toByteArray(objectContent);
        }catch (AmazonServiceException | IOException e  ){
                throw new IllegalStateException("unable to download image"+e);
        }
    }
}
