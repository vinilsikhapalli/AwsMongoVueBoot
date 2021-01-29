package com.teamV.imageUploadAWS.api.UserController;

import com.teamV.imageUploadAWS.api.Beans.User;
import com.teamV.imageUploadAWS.api.UserService.UserService;
import com.teamV.imageUploadAWS.profile.Userprofile;
import com.teamV.imageUploadAWS.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("get-all-users")
    public List<User> getALLUsers(){
        return userService.getALlUsers();
    }

    @PostMapping("save-user")
    public User savePerson(@RequestBody User user){
        System.out.println(user+"---@@");
        user.setModifiedTime(Utils.getCurrentDate());
        if(user.getDeleted() == null) {
            user.setDeleted(false);
            user.setCreatedTime(Utils.getCurrentDate());
        }
        return  userService.save(user);
    }

    @GetMapping("get-user/{userId}")
    public User getPersonByPersonId(@PathVariable("userId") String userId){
        return userService.findById(userId);
    }

    @PatchMapping("delete-user/{userId}")
    public User deletePerson(@PathVariable("userId") String userId){
        User persistedPerson = this.getPersonByPersonId(userId);
        persistedPerson.setDeleted(true);
        return this.savePerson(persistedPerson);
    }
}
