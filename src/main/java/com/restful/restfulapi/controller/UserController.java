package com.restful.restfulapi.controller;

import com.restful.restfulapi.exceptions.UserServiceException;
import com.restful.restfulapi.model.request.UpdateUserDetails;
import com.restful.restfulapi.model.request.UserDetails;
import com.restful.restfulapi.model.response.UserRest;
import com.restful.restfulapi.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;
    public static Map<String, UserRest> users;


    @GetMapping()
    public List<? extends UserRest> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                                             @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        List<UserRest> userList = new ArrayList<>();
        if(users!=null){
            for (Map.Entry<String, UserRest> entry : users.entrySet()) {
                userList.add(entry.getValue());
            }
        }
        return userList;
    }

    @GetMapping(value = "/{userId}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

        /**
         * test my own Exception
         */
       // if(true) throw new UserServiceException("A user service exception is thrown");

        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetails userDetails) {

        UserRest userRest = userService.createUser(userDetails);
        return new ResponseEntity<>(userRest, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}",
            consumes = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public UserRest updateUser(@PathVariable("userId") String userId,
                               @Valid @RequestBody UpdateUserDetails updateUserDetails) {

        UserRest userRest = users.get(userId);
        userRest.setFirstName(updateUserDetails.getFirstName());
        userRest.setLastName(updateUserDetails.getLastName());

        users.put(userId, userRest);

        return userRest;
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") String userId) {
        if(users==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if(users.containsKey(userId)){
           UserRest userRest = users.remove(userId);
           return new ResponseEntity<>(HttpStatus.OK);
        }
        return  ResponseEntity.noContent().build();


    }
}
