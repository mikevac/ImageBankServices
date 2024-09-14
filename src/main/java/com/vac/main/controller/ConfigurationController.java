package com.vac.main.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vac.main.controller.response.ConfigResponse;

@RestController
@RequestMapping("/config")
public class ConfigurationController {

    @Value("${config.baseurl}")
    private String baseUrl;

    @GetMapping("")
    public ConfigResponse getConfig(CsrfToken token) {
        var config = new ConfigResponse();
        config.setBaseUrl(baseUrl);
        config.setContext("");
        config.setToken(token);
        return config;
    }

    @GetMapping("/token")
    public CsrfToken getToken(CsrfToken token) {
        return token;
    }

}
