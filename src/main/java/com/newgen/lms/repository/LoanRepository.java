package com.newgen.lms.repository;

import com.newgen.lms.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long> {

    Loan findByLoanFacilityNameIgnoreCase(String name);

    Loan findByLoanFacilityNameIgnoreCaseAndIdIsNot(String name, Long id);

    @Query(value ="SELECT * FROM loan WHERE is_deleted = false", nativeQuery = true)
    List<Map<?,?>> getList ();

}
