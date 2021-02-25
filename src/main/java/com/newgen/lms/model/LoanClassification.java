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
@Table(name = "LoanClassification")
public class LoanClassification extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String classificationStage;
    private Long duration;
    private Long chargeId;               // foreign key
    private String type;
    private Double percent;
    @Column(length = 50)
    private String remarks;
    private boolean isDeleted = false;
}
