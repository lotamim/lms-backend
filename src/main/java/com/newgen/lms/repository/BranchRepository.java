package com.newgen.lms.repository;

import com.newgen.lms.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Branch findByBranchNameIgnoreCase(String branchName);

    Branch findByBranchNameIgnoreCaseAndIdIsNot(String branchName, Long id);
}
