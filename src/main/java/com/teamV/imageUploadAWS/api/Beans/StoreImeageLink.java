package com.teamV.imageUploadAWS.api.Beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class StoreImeageLink {

    @Id
    private String storeImageLinkId;
    private String userId;
    private String UserLink;
    private Boolean userStatus;


    public String getStoreImageLinkId() {
        return storeImageLinkId;
    }

    public void setStoreImageLinkId(String storeImageLinkId) {
        this.storeImageLinkId = storeImageLinkId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLink() {
        return UserLink;
    }

    public void setUserLink(String userLink) {
        UserLink = userLink;
    }

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }
}
