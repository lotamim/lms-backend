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
@Table(name = "LoanSubType")
public class LoanSubType extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String loanSubTypeName;
    @Column(length = 250)
    private String description;
    private boolean isDeleted = false;
}
