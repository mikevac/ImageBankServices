package com.vac.main.data.entity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import com.vac.main.data.dto.RoleDto;
import com.vac.main.data.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "user", schema = "ibank")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ibank.user_id_seq")
    @Column(name = "user_id")
    private BigDecimal userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "date_established")
    private Date dateEstablished;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "time_zone")
    private String timeZone;

    @OneToMany(mappedBy = "userEntity")
    private Set<UserRoleEntity> userRoleEntities;

    public UserEntity() {

    }

    public UserEntity(UserDto dto, RoleDto roleDto) {
        userId = dto.userId();
        userName = dto.userName();
        firstName = dto.firstName();
        lastName = dto.lastName();
        emailAddress = dto.emailAddress();
        password = dto.password();
        active = dto.isActive();
        dateEstablished = Date.valueOf(dto.dateEstablished());
        deleted = false;
        timeZone = dto.timeZone();
    }

}
