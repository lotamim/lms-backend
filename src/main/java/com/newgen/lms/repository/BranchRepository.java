package com.newgen.lms.repository;

import com.newgen.lms.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findByBranchNameIgnoreCase(String branchName);

    Branch findByBranchNameIgnoreCaseAndIdIsNot(String branchName, Long id);

    @Query(value = " SELECT * FROM branch WHERE is_deleted = false", nativeQuery = true)
    List<Map<?, ?>> getBranchList();
}
