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
            dMap.put("permissionListForRole", permissionListForRole);
            return dMap;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


    public Map save(Map<String, String> dMap) {
        String[] checkedItems = dMap.get("checked").split(",");
        String[] uncheckedItems = dMap.get("unchecked").split(",");
        String roleId = dMap.get("roleId");

        try {
            if (!checkedItems[0].equals("")) {
                for (String itemId : checkedItems) {
                    Permission permission = null;
                    String result = "";
                    permission = userPermissionRepository.findByItemId(Long.parseLong(itemId));
                    if (permission.getRoleId() != null) {
                        String[] ids = permission.getRoleId().split(",");
                        if (ids.length >= 1) {
                            for (String id : ids) {
                                if (id.equals(roleId)) {
                                    continue;
                                } else {
                                    if (!id.equals("")) {
                                        result += id + ",";
                                    } else {
                                        permission.setRoleId(roleId);
                                    }
                                }
                            }
                            if (result != "") {
                                result = result.concat(roleId);
                                permission.setRoleId(result);
                            }
                        }

                    } else {
                        permission.setRoleId(roleId);
                    }
                    userPermissionRepository.save(permission);
                }
            }
            if (!uncheckedItems[0].equals("")) {
                for (String itemId : uncheckedItems) {
                    String result = "";
                    Permission permission = null;
                    permission = userPermissionRepository.findByItemId(Long.parseLong(itemId));
                    if (permission.getRoleId() != null) {
                        String[] ids = permission.getRoleId().split(",");
                        for (String id : ids) {
                            if (id.equals(roleId)) {
                                continue;
                            } else {
                                result += id + ",";
                            }
                        }
                    }
                    if (result != "") {
                        permission.setRoleId(result.substring(0, result.length() - 1));
                    } else {
                        permission.setRoleId(result.substring(0, result.length()));
                    }
                    userPermissionRepository.save(permission);
                }
            }

        } catch (Exception ex) {
            return errorMessage(ERROR, null);
        }
        return successMessage(SUCCESS, null);
    }
}
