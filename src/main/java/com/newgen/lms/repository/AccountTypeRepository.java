package com.newgen.lms.repository;

import com.newgen.lms.model.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
    AccountType findByAccountTypeNameIgnoreCase(String accountTypeName);

    AccountType findByAccountTypeNameIgnoreCaseAndIdIsNot(String accountTypeName, Long id);

    @Query(value = "SELECT id, account_type_name, description, is_deleted\n"
            + "FROM public.account_type\n"
            + "WHERE is_deleted = false", nativeQuery = true)
    List<Map<?,?>> getList();
}
