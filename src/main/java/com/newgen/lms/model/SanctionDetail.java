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
    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date sanctionDate;
    private Long sanctionId;       // foreign key
    private Long divisionId;       // foreign key
    private Long unitId;           // foreign key
    private Long loanTypeId;       // foreign key
    private Long loanSubTypeId;    // foreign key
    private Long chargeId;         // foreign key
    private Double limit = 0.0;
    private String mortgageItem;
    private Double mortgageItemValue = 0.0;
    private Double interestRate = 0.0;
    private boolean isDeleted = false;
}
