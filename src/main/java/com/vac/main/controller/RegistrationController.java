package com.vac.main.controller;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vac.main.controller.request.RegistrationRequest;
import com.vac.main.controller.response.ErrorResponse;
import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.dto.UserDto;
import com.vac.main.encoders.IBPasswordEncoder;
import com.vac.main.services.UserService;
import com.vac.main.services.response.GenericServiceResponse;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private IBPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @ExceptionHandler({ BindException.class })
    public ErrorResponse exceptionHandler() {

        var errorResponse = new ErrorResponse();
        errorResponse.setResponseStatus("FAILED");
        return errorResponse;
    }

    @PostMapping(path = "", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String postRegistration(@Valid @RequestBody RegistrationRequest request) {
        Set<RoleDto> roles = new HashSet<>();
        var establishDate = LocalDate.now();
        var user = new UserDto(null, request.getUserName(), request.getFirstName(), request.getLastName(),
                request.getEmailAddr(), establishDate, passwordEncoder.encode(request.getPassword()), false, "ET",
                roles);
        GenericServiceResponse response = userService.createUser(user);
        return response.message();

    }
}
