package com.vac.main.data.entity;

import java.math.BigDecimal;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * the UserRoleEntity is the intersection between users and roles. Users and
 * roles have a many to many relationship. However, there is intersection data
 * present (expiration date) that must be maintained, so this table is not
 * managed by JPA as a m2m table, but as a one to many table relationship
 * between the userRoleEntity and the userEntity and a one to many table
 * relationship between the userRoleEntity and the roleEntity tables.
 */
@Getter
@Setter
@Entity
@Table(name = "user_role", schema = "ibank")
public class UserRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id")
    private BigDecimal userRoleId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity roleEntity;

    @Column(name = "expiration")
    private Date expiration;

}
