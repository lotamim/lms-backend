package com.newgen.lms.service;

import com.newgen.lms.common.BaseService;
import com.newgen.lms.model.Role;
import com.newgen.lms.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService extends BaseService {
    private static final String SUCCESS = "Data save successfully !";
    private static final String ID_NOT_FOUND = "idNotFound";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String ERROR = "Name can't be duplicate !";

    @Autowired
    public RoleRepository roleRepository;

    public Map<String, String> save(Map<String, String> map) {
        Map<String, String> msgMap = new HashMap<>();
        Role role = null;
        try {
            role = new Role();
            long duplicate = roleRepository.duplicateCheck(map.get("name"));
            if (duplicate >= 1) {
                return errorMessage(ERROR, role);
            }
            role.setName(map.get("name"));
            role.setRemarks(map.get("remarks"));
            roleRepository.save(role);
        } catch (Exception ex) {
            return errorMessage(ex.getMessage(), null);
        }
        return successMessage(SUCCESS, role);
    }

    public Map getRoleList() {
        try {
            Map<String, List<Role>> rMap = new HashMap<>();
            List<Role> roleList = roleRepository.findAll();
            rMap.put("roleList", roleList);
            return rMap;
        } catch (Exception ex) {
            return null;
        }
    }

    public Map<String, String> update(Map<String, String> dMap) {
        Map<String, String> msgMap = new HashMap<>();
        try {
            Role role = roleRepository.findById(Long.parseLong(dMap.get("roleId"))).get();
            if (role == null) {
                msgMap.put(ID_NOT_FOUND, "Role id not found !");
                return msgMap;
            }
            long duplicate = roleRepository.findByNameAndIdNotEqual(dMap.get("name"), Long.parseLong(dMap.get("roleId")));
            if (duplicate >= 1) {
                msgMap.put(ERROR, "Name can't be duplicate !");
                return msgMap;
            }
            role.setName(dMap.get("name"));
            role.setRemarks(dMap.get("remarks"));
            roleRepository.save(role);
            msgMap.put(UPDATE, "Role update successful !");
            return msgMap;
        } catch (Exception ex) {
            msgMap.put(ERROR, ex.getMessage());
            return msgMap;
        }
    }

    public Map<String, String> delete(Map<String, String> dMap) {
        Map<String, String> msgMap = new HashMap<>();
        try {
            Role role = roleRepository.findById(Long.parseLong(dMap.get("roleId"))).get();
            if (role == null) {
                msgMap.put(ID_NOT_FOUND, "Role id not found !");
                return msgMap;
            }
            roleRepository.delete(role);
            msgMap.put(DELETE, "Role delete successful !");
            return msgMap;
        } catch (Exception ex) {
            msgMap.put("error", ex.getMessage());
            return msgMap;
        }
    }
}
