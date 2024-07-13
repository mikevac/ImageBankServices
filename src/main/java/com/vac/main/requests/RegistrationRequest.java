package com.vac.main.requests;

import com.vac.main.constants.RegExp;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationRequest {

    @Pattern(regexp = RegExp.ALPHA_NUMERIC, message = "Invalid characters in UserName.")
    @Size(min = 6, max = 35, message = "User name must be 6 to 35 characters long with no spaces.")
    @NotNull(message = "The user name must be specified.")
    private String userName;

    @Pattern(regexp = RegExp.PASSWORD_CHARS, message = "Invalid characters in password.")
    @Size(min = 8, max = 30, message = "Password must be 8 to 30 characters in length.")
    @NotNull(message = "The password must be specified.")
    private String password;

    @Pattern(regexp = RegExp.EMAIL_ADDR, message = "Email address format is invalid.")
    @Size(min = 7, max = 90, message = "Email address must be 7 to 90 characters in length.")
    @NotNull(message = "The email address must be specified.")
    private String emailAddr;
}
