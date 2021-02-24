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
@Table(name = "Charge")
public class Charge extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String changeName;
    private Double chargeRate = 0.0;
    @Column(length = 250)
    private String remarks;
    private boolean isDeleted = false;
}

