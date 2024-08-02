package com.vac.main.controller.response;

import org.springframework.security.web.csrf.CsrfToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigResponse extends BaseResponse {

    private String context;
    private String baseUrl;
    private CsrfToken token;
}
