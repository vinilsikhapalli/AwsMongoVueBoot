package com.teamV.imageUploadAWS.api.UserService;

import com.teamV.imageUploadAWS.api.Beans.User;
import com.teamV.imageUploadAWS.api.UserDao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getALlUsers() {
        return userDao.findAll().stream().filter(data -> data.getDeleted() != true).collect(Collectors.toList());
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public User findById(String userId) {
        return userDao.findById(userId).orElse(null);
    }
}
