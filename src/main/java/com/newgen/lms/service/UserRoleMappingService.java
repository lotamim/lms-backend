package com.newgen.lms.service;

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
public class UserRoleMappingService {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    @Autowired
    private UserRoleMappingRepository userRoleMappingRepository;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Map<String, String> save(Map<String, String> dMap) {
        Map<String, String> msgMap = new HashMap<>();
        try {
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(dMap.get("username"));
            String[] roleArray = dMap.get("roleList").split(",");
            for (int i = 0; i < roleArray.length; i++) {
                Role role = roleRepository.findByName(roleArray[i]);
                UserRoleMap userRoleMap = new UserRoleMap();
                userRoleMap.setUserId(applicationUser.getId());
                userRoleMap.setRoleId(role.getId());
                userRoleMappingRepository.save(userRoleMap);
            }
            msgMap.put(SUCCESS, "User role mapping successful !");
            return msgMap;
        } catch (Exception ex) {
            msgMap.put(ERROR, ex.getMessage());
            return msgMap;
        }
    }


    public List<Map<?, ?>> getRoleMappingList() {
        return userRoleMappingRepository.roleMappingList();
    }
}
