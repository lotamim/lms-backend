package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.MenuItem;
import com.newgen.lms.model.Permission;
import com.newgen.lms.repository.MenuItemRepository;
import com.newgen.lms.repository.UserPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuItemService extends BaseService {
    private static final String SUCCESS = "Data Save successful !";
    private static final String ERROR = "Data not found !";

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    public Map save(Map<String, String> dMap) {
        MenuItem menuItem = null;
        Permission permission = null;
        try {
            menuItem = new MenuItem();
            menuItem.setMenuId(Long.parseLong(dMap.get("menuId")));
            menuItem.setMenuItemName(dMap.get("menuItemName"));
            menuItem.setMenuItemRemarks(dMap.get("menuItemRemarks"));
            menuItem.setPath(dMap.get("path"));
            menuItemRepository.save(menuItem);

            /*this code for user permission*/
            permission = new Permission();
            permission.setMenuItemId(menuItem.getId());
            permission.setRoleId(null);
            userPermissionRepository.save(permission);

            return successMessage(SUCCESS, menuItem);

        } catch (Exception ex) {
            return errorMessage(ERROR, ex.getMessage());
        }

    }

    public Map getList() {
        Map dMap = new HashMap();
        dMap.put("list", menuItemRepository.list());
        return dMap;
    }

    public Map getDynamicMenuItem() {
        Map dMap = new HashMap();
        dMap.put("list", menuItemRepository.dynamicMenuItem());
        return dMap;
    }

//    public Map getDefaultPermissionList() {
//        Map dMap = new HashMap();
//        dMap.put("defaultPermissionList", menuItemRepository.defaultPermissionList());
//        return dMap;
//    }

}
