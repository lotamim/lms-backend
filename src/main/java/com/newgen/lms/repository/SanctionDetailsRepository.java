package com.newgen.lms.repository;

import com.newgen.lms.model.SanctionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanctionDetailsRepository extends JpaRepository<SanctionDetail, Long> {
}
