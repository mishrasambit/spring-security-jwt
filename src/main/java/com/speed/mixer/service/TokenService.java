package com.speed.mixer.service;

import com.speed.mixer.model.User;

/**
 * Created by sambit on 3/16/2017.
 */
public interface TokenService {
    public String createToken(User user);
}
