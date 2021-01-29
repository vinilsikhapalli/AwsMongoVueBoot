package com.teamV.imageUploadAWS.api.UserDao;

import com.teamV.imageUploadAWS.api.Beans.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserDao extends MongoRepository<User, String> {

}
