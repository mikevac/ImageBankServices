package com.vac.main.data.entity;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "worklist", schema = "ibank")
public class WorkListEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "worklist_id")
    private BigInteger workListId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity userEntity;

    @Column(name = "name")
    private String name;

}
