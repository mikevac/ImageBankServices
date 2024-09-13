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
@Entity(name = "doc")
@Table(name = "doc", schema = "ibank")

public class DocumentEntity {

    @Id
    @Column(name = "doc_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger docId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity owner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private DocTypeEntity docType;

    @Column(name = "worklist_id")
    private BigInteger worklist_id;

    @Column(name = "file_name")
    private String fileName;

}
