package com.vac.main;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * IBCustomAuthenticationEntryPoint
 * 
 * Handles the response of invalid user credentials. By not sending the
 * WWW-Authenticate: Basic realm="xxx" we prevent the browser from popping up a
 * dialog box prompting the user for credentials. Instead, we send a simple
 * response code of SC_UNAUTHORIZED and a message of HTTP Status 401 - Invalid
 * user as a text message.
 */
@Component
public class IBCustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authEx) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authEx.getMessage());
    }

}
