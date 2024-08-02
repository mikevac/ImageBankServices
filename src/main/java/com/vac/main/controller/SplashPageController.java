package com.vac.main.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vac.main.controller.response.ConfigResponse;

@Controller
@RequestMapping("/")
public class SplashPageController {

    @Value("${config.baseurl}")
    private String baseUrl;

    @Value("${server.servlet.context-path}")
    private String serverContext;

    @GetMapping("")
    public ModelAndView getSplashPage() {
        var mav = new ModelAndView();
        mav.setViewName("index.html");
        return mav;
    }

    @GetMapping("/config")
    @ResponseBody
    public ConfigResponse getConfig(CsrfToken token) {
        var config = new ConfigResponse();
        config.setBaseUrl(baseUrl);
        config.setContext(serverContext);
        config.setToken(token);
        return config;

    }

    @GetMapping("/token")
    @ResponseBody
    public CsrfToken getToken(CsrfToken token) {
        return token;
    }

}
