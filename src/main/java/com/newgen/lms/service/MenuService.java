package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.Menu;
import com.newgen.lms.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuService extends BaseService {
    private static final String SUCCESS = "Data Save Successful !";
    private static final String ERROR = "Data not found !";


    @Autowired
    private MenuRepository menuRepository;

    public Map<String, String> save(Map<String, String> dMap) {
        Menu menu = null;
        try {

            if (dMap.get("id") != "") {
                Long id = Long.parseLong(dMap.get("id"));
                menu = menuRepository.findById(id).get();
            } else {
                menu = new Menu();
            }

            menu.setMenuName(dMap.get("name"));
            menu.setRemarks(dMap.get("remarks"));
            menuRepository.save(menu);
            return successMessage(SUCCESS, menu);

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
    }

    public Map list() {
        try {
            Map<String, List<Menu>> rMap = new HashMap<>();
            List<Menu> menuList = menuRepository.findAll();
            rMap.put("menuList", menuList);
            return rMap;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Map<String, String> delete() {
        try {
            return null;

        } catch (Exception ex) {
            return null;
        }
    }

    public Map<String, String> select() {
        try {
            return null;

        } catch (Exception ex) {
            return null;
        }
    }

}
