package com.vac.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLogin() {
        return "login";
    }

    @GetMapping(path = "/fail", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> loginFail() {
        Map<String, Object> parameters = new HashMap<>();

        return parameters;
    }

}
