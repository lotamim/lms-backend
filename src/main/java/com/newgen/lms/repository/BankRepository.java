package com.newgen.lms.repository;

import com.newgen.lms.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    Bank findByBankNameIgnoreCase(String name);

    Bank findByBankNameIgnoreCaseAndIdIsNot(String name, Long id);

    @Query(value ="SELECT * FROM bank WHERE is_delete = false" ,nativeQuery = true)
    List<Map<?,?>> getList ();


}
