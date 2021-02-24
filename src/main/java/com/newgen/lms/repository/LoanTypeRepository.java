package com.newgen.lms.repository;

import com.newgen.lms.model.LoanType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface LoanTypeRepository extends JpaRepository<LoanType, Long> {
    LoanType findByLoanTypeNameIgnoreCase(String name);

    LoanType findByLoanTypeNameIgnoreCaseAndIdIsNot(String name, Long id);

    @Query(value = "SELECT * FROM loan_type WHERE is_deleted = false", nativeQuery = true)
    List<Map<?, ?>> getList();

}
