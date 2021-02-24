package com.newgen.lms.repository;

import com.newgen.lms.model.LoanSubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LoanSubTypeRepository extends JpaRepository<LoanSubType, Long> {
    LoanSubType findByLoanSubTypeNameIgnoreCase(String name);

    LoanSubType findByLoanSubTypeNameIgnoreCaseAndIdIsNot(String name, Long id);

    @Query(value = "SELECT * FROM loan_sub_type WHERE is_deleted = false", nativeQuery = true)
    List<Map<?, ?>> getList();

}
