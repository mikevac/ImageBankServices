package com.vac.main.data.dto;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;

/**
 * UserDto
 * 
 * Transfer object for user information from the database.
 *
 * @param userId          a {@link BigInteger} object that holds the primary key
 *                        of this user in the database
 * @param userName        a {@link String} object that holds the user's name:
 *                        e.g. "JohnnyQ", "MeatHead", etc.
 * @param firstName       a {@link String} object that holds the user's first
 *                        name. may be null as it is an optional field when
 *                        registering. E.g. "Michael", "Jane", etc
 * @param lastName        a {@link String} object that holds the user's last
 *                        name. may be null as it is an optional field when
 *                        registering. E.g. "Smith", "Jones", etc
 * @param emailAddress    a {@link String} object that holds the user's email
 *                        address. used to verify if the user is real. E.g.
 *                        Jane@aol.com
 * @param password        a {@link String} object that holds an encrypted
 *                        password. Most often this field will be null unless
 *                        used in a security context.
 * @param dateEstablished a {@link LocalDate} object that holds the calendar
 *                        date when the user established an account. Will never
 *                        be null.
 * @param isActive        a {@link Boolean} object which indicates if the user
 *                        has been verified and has logged in in the last 365
 *                        days.
 * @param timeZone        a {@link String} object containing the ISO
 *                        abbreviation for a time zone, e.g. EST, GMT
 * @param roles           a {@link Set} object containing one or more
 *                        {@link RoleDto} objects.
 * 
 */
public record UserDto(

        BigInteger userId,
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
