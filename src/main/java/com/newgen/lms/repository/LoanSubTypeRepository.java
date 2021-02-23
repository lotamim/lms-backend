package com.newgen.lms.repository;

import com.newgen.lms.model.LoanSubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanSubTypeRepository extends JpaRepository<LoanSubType,Long> {
}
