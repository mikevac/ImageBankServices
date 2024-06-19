package com.vac.main.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secure/home")
public class HomeController {

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> home() {
        Map<String, Object> parameters = new HashMap<>();

        return parameters;
    }
}
