package com.speed.mixer.controller;

import com.speed.mixer.model.AuthorizedUser;
import com.speed.mixer.model.User;
import com.speed.mixer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by sambit on 3/12/2017.
 */
@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    UserService userService;

    @RequestMapping(value = { "/", "/test" }, method = RequestMethod.GET)
    public Object testOk() {
        return "tested OK";
    }

    @RequestMapping(value = { "/user" }, method = RequestMethod.GET)
    public List<User> listUsers() {
        List<User> users = userService.findAllUsers();
        return users;
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public AuthorizedUser addUsers(@RequestBody User user) {
        AuthorizedUser saveduser = userService.saveUser(user);
        return saveduser;
    }
}
