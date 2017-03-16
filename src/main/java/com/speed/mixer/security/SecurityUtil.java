package com.speed.mixer.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by sambit on 3/16/2017.
 */
@Component
public class SecurityUtil {

    private static final ObjectMapper objectMapper =  new ObjectMapper();

    public static void sendResponse(HttpServletResponse response, int status, Object object) throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(object));
        response.setStatus(status);
        writer.flush();
        writer.close();
    }

    public static void sendError(HttpServletResponse response, Exception exception, int status, String message)throws IOException{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(exception));
        response.setStatus(status);
        writer.flush();
        writer.close();
    }
}
