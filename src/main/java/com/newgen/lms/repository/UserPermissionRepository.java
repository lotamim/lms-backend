package com.newgen.lms.repository;

import com.newgen.lms.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface UserPermissionRepository extends JpaRepository<Permission, Long> {

//    @Query(value = "SELECT\n" +
//            "me.menu_name,\n" +
//            "STRING_AGG(CAST(mi.id AS varchar(30)),',') AS menu_item_id,\n" +
//            "STRING_AGG(mi.menu_item_name||'_'||CAST(mi.id AS varchar(30)),',')AS menu_item_name\n" +
//            "FROM menu AS me\n" +
//            "INNER JOIN menu_item AS mi\n" +
//            "ON mi.menu_id = me.id\n" +
//            "LEFT JOIN permission AS pe\n" +
//            "ON pe.menu_item_id = mi.id\n" +
//            "WHERE pe.role_id =:roleId\n" +
//            "GROUP BY me.menu_name", nativeQuery = true)

    @Query(value = "SELECT \n" +
            "tmp.menu_name,\n" +
            "STRING_AGG(tmp.menu_item_name,',') menu_item_name\n" +
            "FROM (\n" +
            "(SELECT\n" +
            "me.menu_name,\n" +
            "STRING_AGG(mi.menu_item_name||'_'||CAST(mi.id AS varchar)||'_'||CAST('true' AS boolean),',') menu_item_name\n" +
            "FROM permission pe\n" +
            "LEFT JOIN menu_item mi\n" +
            "ON mi.id = pe.menu_item_id\n" +
            "LEFT JOIN menu me\n" +
            "ON me.id = mi.menu_id\n" +
            "WHERE CAST(pe.role_id AS varchar) LIKE('%'||:roleId||'%')\n" +
            "GROUP BY me.menu_name) \n" +
            "UNION ALL\n" +
            "(SELECT \n" +
            "me.menu_name,\n" +
            "STRING_AGG(mi.menu_item_name||'_'||CAST(mi.id AS varchar)||'_'||CAST('false' AS boolean),',')\n" +
            "FROM permission pe\n" +
            "LEFT JOIN menu_item mi\n" +
            "ON mi.id = pe.menu_item_id \n" +
            "LEFT JOIN menu me\n" +
            "ON me.id = mi.menu_id\n" +
            "WHERE pe.id NOT IN (SELECT id FROM permission pe\n" +
            "WHERE CAST(pe.role_id AS varchar) LIKE('%'||:roleId||'%'))\n" +
            "GROUP BY me.menu_name)\n" +
            ") AS tmp\n" +
            "GROUP BY tmp.menu_name\n" +
            "ORDER BY 2,1", nativeQuery = true)
    List<Map<?, ?>> permissionListForRole(@Param("roleId") String roleId);

    @Query(value = "SELECT * FROM permission \n" +
            "WHERE menu_item_id =:itemId", nativeQuery = true)
    Permission findByItemId(@Param("itemId") Long itemId);


    @Query(value ="SELECT * FROM permission\n" +
            "WHERE role_id LIKE('%'||:roleId||'%')" ,nativeQuery = true)
    List<Permission> findAllByRoleId(@Param("roleId") String roleId);

}
