package com.vac.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class SplashPageController {

    @GetMapping("")
    public ModelAndView getSplashPage() {
        return new ModelAndView("index");
    }

}
