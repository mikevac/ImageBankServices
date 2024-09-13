package com.vac.main.data.entity;

import java.math.BigInteger;

import com.vac.main.data.dto.RoleDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private BigInteger roleId;

    @Column(name = "role")
    private String role;

    @Column(name = "desciption")
    private String description;

    public RoleEntity() {

    }

    public RoleEntity(RoleDto dto) {
        roleId = dto.roleId();
        description = dto.description();
        role = dto.role();
    }
}
