package com.newgen.lms.model;


import com.newgen.lms.model.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "Sanction")
@Entity
public class Sanction extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double combineLimit;
    private boolean currency;           // bdt or usd
    private Long bankId;                // foreign key
    private Long branchId;              // foreign key
    private String nature;              // foreign key
    private String method;              // simple & compound
    private String purpose;
    @Column(length = 250)
    private String remarks;
}
