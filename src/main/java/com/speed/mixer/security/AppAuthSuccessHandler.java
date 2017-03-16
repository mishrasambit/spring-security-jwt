package com.speed.mixer.security;

import com.speed.mixer.dao.UserDao;
import com.speed.mixer.model.AuthorizedUser;
import com.speed.mixer.model.User;
import com.speed.mixer.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sambit on 3/16/2017.
 */
@Component
public class AppAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserDao dao;

    @Autowired
    @Qualifier("tokenService")
    private TokenService tokenService;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AuthorizedUser authorizedUser = new AuthorizedUser();
        User user = dao.findByUserEmail(authentication.getName());
        String tokenJWT = tokenService.createToken(user);
        authorizedUser.setEmail(user.getEmail());
        authorizedUser.setFirstname(user.getFirstName());
        authorizedUser.setLastname(user.getLastName());
        authorizedUser.setToken(tokenJWT);
        securityUtil.sendResponse(response, HttpServletResponse.SC_OK, authorizedUser);
    }
}
