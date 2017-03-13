package com.speed.mixer.service.impl;

import com.speed.mixer.constant.ApplicationConstant;
import com.speed.mixer.dao.UserDao;
import com.speed.mixer.model.AuthorizedUser;
import com.speed.mixer.model.User;
import com.speed.mixer.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
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

    @Override
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }

    @Override
    public AuthorizedUser saveUser(User user) {
        AuthorizedUser authorizedUser = new AuthorizedUser();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.saveUser(user);
        String tokenJWT = createToken(user);
        authorizedUser.setEmail(user.getEmail());
        authorizedUser.setFirstname(user.getFirstName());
        authorizedUser.setLastname(user.getLastName());
        authorizedUser.setToken(tokenJWT);
        return authorizedUser;
    }

    private String createToken(User user) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes  = DatatypeConverter.parseBase64Binary(ApplicationConstant.apiSecret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expiredat = new Date(nowMillis + ApplicationConstant.tokenvalidity);
        String compactJws = Jwts.builder()
                .setId(user.getFirstName()+user.getLastName())
                .setIssuedAt(now)
                .setSubject(user.getEmail())
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(expiredat)
                .compact();

        return compactJws;
    }
}
