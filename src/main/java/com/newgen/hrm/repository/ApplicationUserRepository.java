package com.newgen.hrm.repository;

import com.newgen.hrm.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByUsername(String Username);

    @Query(value = "SELECT count(*)  FROM application_user as au \n" +
            "WHERE au.username ILIKE(:username)", nativeQuery = true)
    int duplicateCheck(@Param("username") String username);
}
