package com.teamV.imageUploadAWS.mediaConfig.userProfileDao;

import com.teamV.imageUploadAWS.api.Beans.StoreImeageLink;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaveImageLinkInDB extends MongoRepository<StoreImeageLink, String> {
}
