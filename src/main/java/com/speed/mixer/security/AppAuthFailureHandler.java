package com.speed.mixer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sambit on 3/16/2017.
 */
@Component
public class AppAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        securityUtil.sendError(response,exception, HttpServletResponse.SC_UNAUTHORIZED, "Userid/Password not found!");
    }
}
