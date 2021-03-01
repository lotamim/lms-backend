package com.newgen.lms.repository;

import com.newgen.lms.model.Charge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChargeRepository extends JpaRepository<Charge, Long> {


    Charge findByChargeNameIgnoreCase(String name);

    Charge findByChargeNameIgnoreCaseAndIdIsNot(String name, Long id);

    @Query(value = "SELECT * FROM charge WHERE is_deleted = false", nativeQuery = true)
    List<Map<?, ?>> getList();

}
