package com.restful.restfulapi.userservice;

import com.restful.restfulapi.model.request.UserDetails;
import com.restful.restfulapi.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetails userDetails);
}
