package com.newgen.lms.repository;

import com.newgen.lms.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    @Query(value = "SELECT \n"
            + "mi.id as menu_item_id,me.id as menu_id,\n"
            + "mi.menu_item_name,me.menu_name,mi.path\n"
            + "FROM menu_item AS mi\n"
            + "INNER JOIN menu AS me\n"
            + "ON me.id = mi.menu_id", nativeQuery = true)
    List<Map<?, ?>> list();

    @Query(value = "SELECT \n" +
            "me.menu_name ,\n" +
            "STRING_AGG(mi.menu_item_name,',') AS menu_item_name,\n" +
            "STRING_AGG(mi.path,',') AS path \n" +
            "FROM menu_item AS mi\n" +
            "INNER JOIN menu AS me\n" +
            "ON me.id = mi.menu_id\n" +
            "INNER JOIN permission AS pe\n" +
            "ON pe.menu_item_id = mi.id\n" +
            "WHERE CAST(pe.role_id AS varchar) LIKE ('%'||:roleId||'%')\n" +
            "GROUP BY me.menu_name", nativeQuery = true)
    List<Map<?, ?>> dynamicMenuItem(@Param("roleId") Long roleId);
}
