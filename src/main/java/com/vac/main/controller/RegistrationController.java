package com.vac.main.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vac.main.requests.RegistrationRequest;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @PostMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String postRegistration(@Valid RegistrationRequest request) {
        return "success";

    }
}
