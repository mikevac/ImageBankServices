package com.vac.main.data.dto;

import java.math.BigInteger;

/**
 * RoleDto
 *
 * Transfer object to represent a single role associated to a user. Roles expire
 * based upon payments and fees. if the expiration field is null, the role does
 * not expire.
 * 
 * @param roleId an {@link BigInteger} object that holds the primary key of a
 *               role
 * @param role   a {@link String} object holding the name of the role: e.g.
 *               "USER"
 */

public record RoleDto(

        BigInteger roleId,
        String description,
        String role) {
}
