package com.speed.mixer.service.impl;

import com.speed.mixer.dao.UserDao;
import com.speed.mixer.model.AuthorizedUser;
import com.speed.mixer.model.User;
import com.speed.mixer.service.TokenService;
import com.speed.mixer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by sambit on 2/10/2017.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @Override
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public AuthorizedUser saveUser(User user) {
        AuthorizedUser authorizedUser = new AuthorizedUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.saveUser(user);
        String tokenJWT = tokenService.createToken(user);
        authorizedUser.setEmail(user.getEmail());
        authorizedUser.setFirstname(user.getFirstName());
        authorizedUser.setLastname(user.getLastName());
        authorizedUser.setToken(tokenJWT);
        return authorizedUser;
    }
}
