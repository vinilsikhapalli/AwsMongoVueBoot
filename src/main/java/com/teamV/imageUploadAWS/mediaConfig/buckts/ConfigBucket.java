package com.teamV.imageUploadAWS.mediaConfig.buckts;

public enum ConfigBucket {

    //PROFILE_IMAGE("teamv-123-test");
    PROFILE_IMAGE("teamv-123-test-mumbai");
    private final String buckerName;

    public String getBuckerName() {
        return buckerName;
    }

    ConfigBucket(String buckerName) {
        this.buckerName = buckerName;
    }
}
