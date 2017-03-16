package com.speed.mixer.dao;

import com.speed.mixer.model.User;

import java.util.List;

/**
 * Created by sambit on 2/10/2017.
 */
public interface UserDao {
    List<User> findAllUsers();

    void saveUser(User user);

    User findByUserEmail(String email);
}
