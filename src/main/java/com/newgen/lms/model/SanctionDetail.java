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
@Table(name = "SanctionDetail")
@Entity
public class SanctionDetail extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date sanctionDate;
    private Long sanctionId;       // foreign key
    private Long divisionId;       // foreign key
    private Long unitId;           // foreign key
    private Long loanTypeId;       // foreign key
    private Long loanSubTypeId;    // foreign key
    private Long chargeId;         // foreign key
    private Double limit;
    private String mortgageItem;
    private Double mortgageItemValue;
    private Double interestRate;
    private boolean isDeleted = false;
}
