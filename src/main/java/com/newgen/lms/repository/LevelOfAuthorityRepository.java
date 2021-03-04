package com.newgen.lms.repository;

import com.newgen.lms.model.LevelOfAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LevelOfAuthorityRepository extends JpaRepository<LevelOfAuthority,Long> {
}
