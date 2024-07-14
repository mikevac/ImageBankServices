package com.vac.main.data.dto;

import java.math.BigDecimal;

/**
 * <h1>RoleDto</h1>
 * <p>
 * Transfer object to represent a single role associated to a user. Roles expire
 * based upon payments and fees. if the expiration field is null, the role does
 * not expire.
 * </p>
 */

public record RoleDto(

        BigDecimal roleId,
        String role) {
}
