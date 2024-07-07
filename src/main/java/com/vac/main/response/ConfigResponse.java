package com.vac.main.response;

import org.springframework.security.web.csrf.CsrfToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigResponse {

    private String context;
    private String baseUrl;
    private CsrfToken token;
}
