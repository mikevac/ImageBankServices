package com.vac.main.data.dto;

/**
 * <h1>UserDto</h1>
 * <p>
 * Transfer object for users from the database.
 * </p>
 */
public record UserDto(
        Integer userId,
        String password,
        String userName,
        String emailAddress,
        String phoneNumber,
        String realName
) {
}
