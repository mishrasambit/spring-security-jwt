package com.speed.mixer.service.impl;

import com.speed.mixer.constant.ApplicationConstant;
import com.speed.mixer.model.User;
import com.speed.mixer.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by sambit on 3/16/2017.
 */
@Component("tokenService")
public class TokenServiceImpl implements TokenService{

    @Override
    public String createToken(User user) {
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
