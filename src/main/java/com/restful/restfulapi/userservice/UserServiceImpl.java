package com.restful.restfulapi.userservice;

import com.restful.restfulapi.model.request.UserDetails;
import com.restful.restfulapi.model.response.UserRest;
import com.restful.restfulapi.shared.Utils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

import static com.restful.restfulapi.controller.UserController.users;

@Service
public class UserServiceImpl implements UserService{

    Utils utils;

    public UserServiceImpl(){}
    // constructor dependency injection
    public UserServiceImpl(Utils utils) {
        this.utils=utils;
    }

    @Override
    public UserRest createUser(UserDetails userDetails) {

        UserRest userRest = new UserRest();
        userRest.setEmail(userDetails.getEmail());
        userRest.setLastName(userDetails.getLastName());
        userRest.setFirstName(userDetails.getFirstName());

        //simulate db primary key (id)
        String userId = utils.generateUserId();
        userRest.setUserId(userId);

        //check if we have an instance of Map
        if (users == null) {
            users = new HashMap<>();
            users.put(userId, userRest);
        } else {
            users.put(userId, userRest);
        }
        return userRest;
    }
}
