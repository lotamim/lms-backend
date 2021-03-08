package com.newgen.lms.model;

import com.newgen.lms.model.audit.Auditable;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "LonaDisbursement")
public class LonaDisbursement extends Auditable<String> {
    private Long id;
    private String disbursementRefNo;
    private Date disbursementDate; //
    private Long sanctionId;
    private Double loanAmount;
    private Double disbursementCurrency;
    private Double conversionRate;
    private Double loanAmountInBdt;
    private Date expiryDate;
    private boolean isDeleted = false;
    private String status ;
}
