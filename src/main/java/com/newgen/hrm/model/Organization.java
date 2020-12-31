package com.newgen.hrm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String organizationName;
    private String email;
    private String phoneNumber;
    private String address;
    private String remarks;
    private String organizationLogo;
}
