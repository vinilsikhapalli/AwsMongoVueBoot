package com.teamV.imageUploadAWS.profile;

import com.teamV.imageUploadAWS.mediaConfig.userProfileService.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Userprofile {

    private UUID userProfileid;
    private String userName;
    private String profileLink;

    public Userprofile(UUID userProfileid, String userName, String profileLink) {
        this.userProfileid = userProfileid;
        this.userName = userName;
        this.profileLink = profileLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Userprofile that = (Userprofile) o;
        return Objects.equals(userProfileid , that.userProfileid) &&
                Objects.equals( userName, that.userName) &&
               Objects.equals( profileLink , that.profileLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userProfileid, userName, profileLink);
    }

    public UUID getUserProfileid() {
        return userProfileid;
    }

    public void setUserProfileid(UUID userProfileid) {
        this.userProfileid = userProfileid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Optional<String> getProfileLink() {
        return Optional.ofNullable(profileLink);
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

}
