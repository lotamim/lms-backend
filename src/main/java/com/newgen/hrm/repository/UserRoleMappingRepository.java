package com.newgen.hrm.repository;

import com.newgen.hrm.model.UserRoleMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMap,Long> {
    @Query(value = "SELECT \n"
            + "au.id applicaion_user_id,\n"
            + "string_agg(ro.name,',') role_name,\n"
            + "au.username user_name \n"
            + "FROM user_role ur\n"
            + "INNER JOIN application_user au\n"
            + "ON au.id = ur.user_id\n"
            + "INNER JOIN role ro\n"
            + "ON ro.id = ur.role_id\n"
            + "GROUP BY au.username,au.id",nativeQuery = true)
    List<Map<?,?>> roleMappingList();
}
