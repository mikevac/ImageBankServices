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
@Entity(name = "docType")
@Table(name = "doc_type", schema = "ibank")
public class DocTypeEntity {

    @Id
    @Column(name = "doc_type_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger docTypeId;

    @Column(name = "doc_type")
    private String docType;

    @Column(name = "file_ext_default")
    private String fileExtDefault;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity user;
}
