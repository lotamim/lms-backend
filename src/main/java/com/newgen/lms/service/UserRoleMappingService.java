package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.ApplicationUser;
import com.newgen.lms.model.Role;
import com.newgen.lms.model.UserRoleMap;
import com.newgen.lms.repository.ApplicationUserRepository;
import com.newgen.lms.repository.RoleRepository;
import com.newgen.lms.repository.UserRoleMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRoleMappingService extends BaseService {
    private static final String SUCCESS = "User role mapping successful !";
    private static final String ERROR = "error";
    private static final String USER_ID_NOT_FOUND = "User id not found !";
    private static final String ROLE_ID_NOT_FOUND = "Role id not found !";

    @Autowired
    private UserRoleMappingRepository userRoleMappingRepository;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Map<String, String> save(Map<String, String> dMap) {
        Map<String, String> msgMap = new HashMap<>();
        UserRoleMap userRoleMap = null;
        try {
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(dMap.get("username"));
            if (applicationUser == null) {
                return errorMessage(USER_ID_NOT_FOUND, userRoleMap);
            }
            Role role = roleRepository.findByName(dMap.get("name"));
            if (role == null) {
                return errorMessage(ROLE_ID_NOT_FOUND, userRoleMap);
            }
            userRoleMap = new UserRoleMap();
            userRoleMap.setUserId(applicationUser.getId());
            userRoleMap.setRoleId(role.getId());
            userRoleMappingRepository.save(userRoleMap);

        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), userRoleMap);
        }
        return successMessage(SUCCESS, userRoleMap);
    }


    public List<Map<?, ?>> getRoleMappingList() {
        return userRoleMappingRepository.roleMappingList();
    }

    public Map<String, String> update(Map<String, String> dMap) {
        UserRoleMap userRoleMap = null;
        try {

            ApplicationUser applicationUser = applicationUserRepository.findByUsername(dMap.get("username"));
            if (applicationUser == null) {
                return errorMessage(USER_ID_NOT_FOUND, userRoleMap);
            }
            Role role = roleRepository.findByName(dMap.get("name"));
            if (role == null) {
                return errorMessage(ROLE_ID_NOT_FOUND, userRoleMap);
            }
            userRoleMap = userRoleMappingRepository.findById(Long.parseLong(dMap.get("id"))).get();
            userRoleMap.setUserId(applicationUser.getId());
            userRoleMap.setRoleId(role.getId());
            userRoleMappingRepository.save(userRoleMap);
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), userRoleMap);
        }
        return successMessage(SUCCESS, userRoleMap);
    }

}
