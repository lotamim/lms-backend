package com.newgen.lms.repository;

import com.newgen.lms.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    Unit findByUnitNameIgnoreCase(String name);

    Unit findByUnitNameIgnoreCaseAndIdIsNot(String name, Long id);

    @Query(value ="SELECT * FROM unit WHERE is_delete = false" ,nativeQuery = true)
    List<Map<?,?>> getList ();

}
