package com.vac.main.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse extends BaseResponse {

    private String errorMessage;

}
