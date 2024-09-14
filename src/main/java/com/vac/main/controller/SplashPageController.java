package com.vac.main.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class SplashPageController {

    @Value("${config.baseurl}")
    private String baseUrl;

    @GetMapping(path = "")
    @ResponseBody
    public String getSplashPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        var outputBuffer = new StringBuilder();
        var inStream = SplashPageController.class.getResourceAsStream("/static/index.html");
        var bufferedReader = new BufferedReader(new InputStreamReader(inStream));
        String line = bufferedReader.readLine();
        while (line != null) {
            outputBuffer.append(line);
            line = bufferedReader.readLine();
        }

        return outputBuffer.toString();
    }

}
