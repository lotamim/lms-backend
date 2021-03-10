package com.newgen.lms.repository;

import com.newgen.lms.model.LoanDisbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDisbursementRepository extends JpaRepository<LoanDisbursement, Long> {

    @Query(value = "SELECT sand.limit \n" +
            "FROM sanction_detail AS sand\n" +
            "INNER JOIN sanction AS san\n" +
            "ON san.id = sand.sanction_id\n" +
            "WHERE sand.sanction_id =:sanctionId AND sand.unit_id =:unitId AND is_deleted = false", nativeQuery = true)
    Double checkLoanLimitForUnit(@Param("sanctionId") Long sanctionId, @Param("unitId") Long unitId);


    @Query(value = "SELECT\n" +
            "SUM(loan_amount_in_bdt)\n" +
            "FROM loan_disbursement\n" +
            "WHERE disbursement_ref_no =:disbursementRef AND is_deleted = false", nativeQuery = true)
    Double totalAmountInDisbursementRef(@Param("disbursementRef") String disbursementRef);

    @Query(value = "SELECT\n" +
            "SUM(loan_amount_in_bdt)\n" +
            "FROM loan_disbursement\n" +
            "WHERE sanction_id =:sanctionId AND is_deleted = false", nativeQuery = true)
    Double totalAmountInSanctionRef(@Param("sanctionId") Long sanctionId);

    @Query(value = "SELECT combine_limit\n" +
            "from sanction\n" +
            "WHERE id =:sanctionId", nativeQuery = true)
    Double totalLimitForSanctionRef(@Param("sanctionId") Long sanctionId);


}
