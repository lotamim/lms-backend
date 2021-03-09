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
@Table(name = "LoanDisbursement")
public class LoanDisbursement extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String disbursementRefNo;
    private Date disbursementDate; //
    private Long sanctionId;
    private Long unitId;
    private Double loanAmount = 0.0;
    private String disbursementCurrency;
    private Double conversionRate;
    private Double loanAmountInBdt = 0.0;
    private Date expiryDate;
    private boolean isDeleted = false;
    private String status;
}
