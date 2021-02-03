package com.newgen.lms.repository;

import com.newgen.lms.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query(value = "SELECT count(*) FROM menu\n"
            + "WHERE LOWER(menu_name)=LOWER(:name)", nativeQuery = true)
    long duplicateCheck(@Param("name") String name);

    @Query(value = "SELECT count(*) FROM menu\n"
            + "WHERE LOWER(menu_name)=LOWER(:name) AND id NOT IN(:id)", nativeQuery = true)
    long findByNameAndIdNotEqual(@Param("name") String name, @Param("id") Long id);

}
