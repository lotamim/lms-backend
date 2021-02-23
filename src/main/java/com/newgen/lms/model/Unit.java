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
@Table(name = "Unit")
public class Unit extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String unitName;
    @Column(length = 10)
    private String unitShortName;
    @Column(length = 250)
    private String address;
    @Column(length = 15)
    private String phoneNumber;
    @Column(length = 50)
    private String contactPerson;
    @Column(length = 250)
    private String description;
    private boolean isDeleted = false;
}