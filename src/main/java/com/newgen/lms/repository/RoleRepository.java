package com.newgen.lms.repository;

import com.newgen.lms.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query(value = "SELECT count(*) FROM role\n"
                   +"WHERE LOWER(name)=LOWER(:name)",nativeQuery = true)
    long duplicateCheck (@Param("name") String name);

    @Query(value = "SELECT count(*) FROM role\n"
                   +"WHERE LOWER(name)=LOWER(:name) AND id NOT IN(:roleId)", nativeQuery = true)
    long findByNameAndIdNotEqual (@Param("name") String name, @Param("roleId")Long roleId);

    Role findByName (String name);
}
