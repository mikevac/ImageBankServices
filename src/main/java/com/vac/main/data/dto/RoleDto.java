package com.vac.main.data.dto;


import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>RoleDto</h1>
 * <p>
 * Transfer object to represent a single role associated to a user.  Roles
 * expire based upon payments and fees.  if the expiration field is null,
 * the role does not expire.
 * </p>
 */
@Getter
@Setter
public class RoleDto {

    public Integer userRoleId;
    public Integer userId;
    public String role;
    public OffsetDateTime expiration;
    
}
