package com.newgen.lms.repository;

import com.newgen.lms.model.Charge;
import com.newgen.lms.model.Loan;
import com.newgen.lms.model.LoanClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface LoanClassificationRepository extends JpaRepository<LoanClassification, Long> {

    LoanClassification findByClassificationStageIgnoreCase(String name);

    LoanClassification findByClassificationStageIgnoreCaseAndIdIsNot(String name, Long id);

    @Query(value = "SELECT * FROM loan_classification WHERE is_deleted = false", nativeQuery = true)
    List<Map<?, ?>> getList();

}
