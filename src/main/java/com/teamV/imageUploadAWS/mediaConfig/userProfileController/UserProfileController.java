package com.teamV.imageUploadAWS.mediaConfig.userProfileController;

import com.teamV.imageUploadAWS.mediaConfig.userProfileService.UserProfileService;
import com.teamV.imageUploadAWS.profile.Userprofile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user-profile")
@CrossOrigin("*")
public class UserProfileController {

    @Autowired
    private UserProfileService service;


    @PostMapping(path = "/{userProfileId}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void imageUploadToAWS(@PathVariable("userProfileId") String userProfileId,
                                 @RequestParam("file") MultipartFile file) throws IllegalAccessException {
        //System.out.println("getting here....!" + userProfileId + " - " + file);
        service.uploadProfileToAWS(userProfileId, file);
    }

    @GetMapping("/{userProfileId}/image/download")
    public byte[] imageDownloadfromAWS(@PathVariable("userProfileId") String userProfileId) {
        byte[] bytes = service.imagedownloadFromAWS(userProfileId);
      //  System.out.println(bytes + " calling here...*");
        return bytes;
    }
}
