package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.ApplicationUser;
import com.newgen.lms.model.MenuItem;
import com.newgen.lms.model.Permission;
import com.newgen.lms.model.UserRoleMap;
import com.newgen.lms.repository.ApplicationUserRepository;
import com.newgen.lms.repository.MenuItemRepository;
import com.newgen.lms.repository.UserPermissionRepository;
import com.newgen.lms.repository.UserRoleMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MenuItemService extends BaseService {
    private static final String SUCCESS = "Data Save successful !";
    private static final String ERROR = "Data not found !";

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Autowired
    private UserRoleMappingRepository userRoleMappingRepository;


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
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(name);
        UserRoleMap userRoleMap = userRoleMappingRepository.findByUserId(applicationUser.getId());
        dMap.put("list", menuItemRepository.dynamicMenuItem(userRoleMap.getRoleId()));
        return dMap;
    }

}
