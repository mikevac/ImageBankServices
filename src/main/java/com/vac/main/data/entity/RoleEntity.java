package com.vac.main.data.entity;

import java.math.BigInteger;
import java.util.Set;

import com.vac.main.data.dto.RoleDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * The role entity represents a row in the role table.
 */
@Getter
@Setter
@Entity(name = "role")
@Table(name = "role", schema = "ibank")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ibank.role_id_seq")
    @Column(name = "role_id")
    private BigInteger roleId;

    @OneToMany(mappedBy = "roleEntity")
    private Set<UserRoleEntity> userRoles;

    @Column(name = "ROLE")
    private String role;

    public RoleEntity() {

    }

    public RoleEntity(RoleDto dto) {
        roleId = dto.roleId();
        role = dto.role();
    }
}
