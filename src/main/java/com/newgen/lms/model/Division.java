package com.newgen.lms.model;

import com.newgen.lms.model.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Division")
public class Division extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String divisionName;
    @Column(length = 50)
    private String contactPerson;
    private String email;
    @Column(length = 15)
    private String phoneNumber;
    @Column(length = 250)
    private String description;
    private boolean isDelete = false;
}