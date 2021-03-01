package com.newgen.lms.model;


import com.newgen.lms.model.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Sanction")
public class Sanction extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date sanctionDate;
    private Double combineLimit;
    private boolean currency;           // bdt or usd
    private String purpose;
    private Long bankId;                // foreign key
    private Long branchId;              // foreign key
    private Long divisionId;            // foreign key
    private Long unitId;                // foreign key
    private Long LoanId;                // foreign key
    private Long loanTypeId;            // foreign key
    private boolean nature;             // foreign key
    private Double sanctionAmount;
    private Double interestRate;
    private boolean method;             // simple & compound
    private Long chargeId;              // foreign key
    private Double mortgageItemValue;    //
    @Column(length = 250)
    private String remarks;
    private boolean isDeleted = false;

}
