package com.vac.main.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * BaseResponse
 * 
 * All responses to the client require a response status.
 */
public class BaseResponse {

    private String responseStatus;

}
