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
@Table(name = "Account")
public class Account extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bankId;            // reference id Or foreign-key
    private Long branchId;          // reference id Or foreign-key
    private Long accountTypeId;     // reference id Or foreign-key
    private Long divisionId;        // reference id Or foreign-key
    private Long unitId;            // reference id Or foreign-key
    private String accountNumber;
    private Double accountBalance;
    private boolean isDeleted = false;
}
