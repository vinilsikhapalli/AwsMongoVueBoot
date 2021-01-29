package com.teamV.imageUploadAWS.mediaConfig.userProfileService;

import com.teamV.imageUploadAWS.api.Beans.StoreImeageLink;
import com.teamV.imageUploadAWS.api.Beans.User;
import com.teamV.imageUploadAWS.api.UserDao.UserDao;
import com.teamV.imageUploadAWS.api.UserService.UserService;
import com.teamV.imageUploadAWS.mediaConfig.buckts.ConfigBucket;
import com.teamV.imageUploadAWS.mediaConfig.fileStores.FileStore;
import com.teamV.imageUploadAWS.profile.Userprofile;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class UserProfileService {

    @Autowired
    private FileStore fileStore;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao repo;

    public void uploadProfileToAWS(String userProfileId, MultipartFile file) throws IllegalAccessException {
        // 1. check image has empty or not?
        if(file.isEmpty()){
            throw new IllegalAccessException(" file not found ");
        }
        // 2.if the file is an image or not?
        if(!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(),ContentType.IMAGE_GIF.getMimeType(),
                ContentType.IMAGE_PNG.getMimeType()).contains(file.getContentType())){
            throw new IllegalAccessException("not an  image");
        }

        // 3.the user existing in db or not?

        User selectedUser = getuserByUserId(userProfileId);

        // 4.grab metadata from file!
        HashMap<String,String>  metadata =  new HashMap<>();
        metadata.put("Content-Type" , file.getContentType());
        metadata.put("Content-Length" , String.valueOf(file.getSize()));

        // 5. store the image in S3 and update with image id in id
        String path = String.format("%s/%s", ConfigBucket.PROFILE_IMAGE.getBuckerName(), selectedUser.getUserName());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            fileStore.saveFIle(path,fileName, Optional.of(metadata),file.getInputStream());
            selectedUser.setImageLink(fileName);
            this.StoreImageLinkInDB(path,userProfileId);
        } catch (IOException e) {
            throw new IllegalAccessException("file upload issue "+e);
        }
    }


    public byte[] imagedownloadFromAWS(String userProfileId)  {
        User userprofile = this.getuserByUserId(userProfileId);

        String path = String.format("%s/%s",
                ConfigBucket.PROFILE_IMAGE.getBuckerName(),
                userprofile.getUserName());

       return fileStore.imageDownload(path,userProfileId);
         //       .OrE(new byte[0]);
    }

    private User getuserByUserId(String userProfileId) {

        try {
            return repo.findById(userProfileId)
                    .orElseThrow(() -> new IllegalAccessException(String.format(" user not found")));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void StoreImageLinkInDB(String path, String userId) {

        User persistedUser = userService.findById(userId);
        if(persistedUser != null) {
            persistedUser.setImageLink(path);
            userService.save(persistedUser);
        }
    }

}
