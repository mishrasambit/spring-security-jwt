package com.speed.mixer.security;

import com.speed.mixer.dao.UserDao;
import com.speed.mixer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by sambit on 3/16/2017.
 */
@Component("userDetailsService")
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = userDao.findByUserEmail(email);
        org.springframework.security.core.userdetails.User userdetails = null;
        if(user==null){
            throw new UsernameNotFoundException("Username not found exception!");
        }else{
            userdetails = new org.springframework.security.core.userdetails.User(email, user.getPassword(),null);
        }
        return userdetails;
    }
}
