package com.newgen.lms.repository;

import com.newgen.lms.model.Account;
import com.newgen.lms.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByAccountNumberIgnoreCase(String name);
    Account findByAccountNumberIgnoreCaseAndBankId(String accNumber,Long bankId);
    Account findByAccountNumberIgnoreCaseAndIdIsNot(String name, Long id);

    @Query(value ="SELECT \n" +
            "acc.id,\n" +
            "bnk.bank_name,\n" +
            "brn.branch_name,\n" +
            "dvn.division_name,\n" +
            "unt.unit_name,\n" +
            "act.account_type_name,\n" +
            "acc.account_number,\n"+
            "acc.account_balance\n"+
            "FROM account  as acc\n" +
            "INNER JOIN bank as bnk\n" +
            "ON bnk.id = acc.bank_id\n" +
            "INNER JOIN branch as brn\n" +
            "ON brn.id = acc.branch_id\n" +
            "INNER JOIN division as dvn\n" +
            "ON dvn.id = acc.division_id\n" +
            "INNER JOIN unit as unt\n" +
            "ON unt.id = acc.unit_id\n" +
            "INNER JOIN account_type as act\n" +
            "ON act.id = acc.account_type_id\n" +
            "WHERE acc.is_deleted = false\n", nativeQuery = true)
    List<Map<?,?>> getList ();

}
