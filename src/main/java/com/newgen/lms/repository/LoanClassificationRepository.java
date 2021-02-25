package com.newgen.lms.repository;

import com.newgen.lms.model.LoanClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanClassificationRepository extends JpaRepository<LoanClassification, Long> {
}
