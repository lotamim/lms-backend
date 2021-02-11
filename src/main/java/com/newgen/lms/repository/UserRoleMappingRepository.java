package com.newgen.lms.repository;

import com.newgen.lms.model.UserRoleMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMap,Long> {
    @Query(value = "SELECT \n"
            + "ur.id,\n"
            + "string_agg(ro.name,',') AS name,\n"
            + "au.username\n"
            + "FROM user_role ur\n"
            + "INNER JOIN application_user au\n"
            + "ON au.id = ur.user_id\n"
            + "INNER JOIN role ro\n"
            + "ON ro.id = ur.role_id\n"
            + "GROUP BY au.username,ur.id",nativeQuery = true)
    List<Map<?,?>> roleMappingList();

    UserRoleMap findByRoleId(Long roleId);

    UserRoleMap findByUserId(Long userId);
}
