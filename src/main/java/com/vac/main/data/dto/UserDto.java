package com.vac.main.data.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * <h1>UserDto</h1>
 * <p>
 * Transfer object for users from the database.
 * </p>
 */
public record UserDto(

        BigDecimal userId,
        String userName,
        String firstName,
        String lastName,
        String emailAddress,
        LocalDate dateEstablished,
        String password,
        Boolean isActive,
        String timeZone,
        Set<RoleDto> roles) {
}
