package com.newgen.lms.model;


import com.newgen.lms.common.Status;
import com.newgen.lms.common.Type;
import com.newgen.lms.model.audit.Auditable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "LevelOfAuthority")
public class LevelOfAuthority extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sanctionId;  // foreign key
    private Long employeeId;  // foreign key
    private boolean isDeleted = false;
    private boolean isActive = true;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Type type;
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Status status;
}
