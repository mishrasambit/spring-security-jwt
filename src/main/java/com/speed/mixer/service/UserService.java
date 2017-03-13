package com.speed.mixer.service;

import com.speed.mixer.model.AuthorizedUser;
import com.speed.mixer.model.User;

import java.util.List;

/**
 * Created by sambit on 2/10/2017.
 */
public interface UserService {
    List<User> findAllUsers();

    AuthorizedUser saveUser(User user);
}
