package com.newgen.lms.repository;

import com.newgen.lms.model.Division;
import com.newgen.lms.model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DivisionRepository extends JpaRepository<Division,Long> {

    Division findByDivisionNameIgnoreCase(String name);

    Division findByDivisionNameIgnoreCaseAndIdIsNot(String name, Long id);

    @Query(value ="SELECT * FROM division WHERE is_deleted = false" ,nativeQuery = true)
    List<Map<?,?>> getList ();

}
