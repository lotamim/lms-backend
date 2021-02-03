package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.Permission;
import com.newgen.lms.repository.MenuItemRepository;
import com.newgen.lms.repository.UserPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserPermissionService extends BaseService {
    private static final String SUCCESS = "Data save successful !";
    private static final String ERROR = "Fail to data save !";

    @Autowired
    private UserPermissionRepository userPermissionRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public Map getPermissionListForRole(String roleId) {
        Map dMap = new HashMap();
        List<Map<?, ?>> permissionListForRole;
        try {
            permissionListForRole = userPermissionRepository.permissionListForRole(roleId);
//            if (permissionListForRole.size() == 0) {
//                permissionListForRole = menuItemRepository.defaultPermissionList();
//            }

            dMap.put("permissionListForRole", permissionListForRole);
            return dMap;

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


    public Map save(Map<String, String> dMap) {
        String[] items = dMap.get("items").split(",");
        String roleId = dMap.get("roleId");

        try {
            for (String itemId : items) { // Note : আইটেম & রোল আইডি দিয়ে খুঁজে আনতে হবে
                Permission permission = null;
                permission = userPermissionRepository.findByItemId(Long.parseLong(itemId));
                if (permission.getRoleId() != null) {
//                    String id = permission.getRoleId();
                    String[] ids = permission.getRoleId().split(",");
                    if (ids.length >= 1) {
                        for (String dd : ids) {
                            if (dd.equals(roleId)) {
                                continue;
                            } else {
                                permission.setRoleId(dd + "," + roleId);
                            }
                        }
                    }
                } else {
                    permission.setRoleId(roleId);
                }
                userPermissionRepository.save(permission);
            }
            return successMessage(SUCCESS, null);
        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
    }

}
