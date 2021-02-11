package com.newgen.lms.repository;

import com.newgen.lms.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu findByMenuNameIgnoreCase(String name);

    Menu findByMenuNameIgnoreCaseAndIdIsNot(String name, Long id);
}
