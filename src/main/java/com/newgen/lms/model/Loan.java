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
@Table(name = "Loan")
public class Loan extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long loanTypeId;      // foreign key
    private Long loanSubTypeId;   // foreign key
    @Column(length = 50)
    private String loanFacilityName;
    private boolean isDeleted = false;
}
