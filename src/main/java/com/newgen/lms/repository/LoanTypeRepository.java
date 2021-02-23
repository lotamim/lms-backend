package com.newgen.lms.repository;

import com.newgen.lms.model.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanTypeRepository extends JpaRepository<LoanType, Long> {
}
