package com.vac.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vac.main.controller.request.CredentialRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getLogin() {
        return "login";
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@Valid CredentialRequest creds) {

        return "success";
    }

    @GetMapping(path = "/fail", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> loginFail() {
        Map<String, Object> parameters = new HashMap<>();

        return parameters;
    }

}
