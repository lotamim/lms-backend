package com.newgen.hrm.service;

import com.newgen.hrm.model.Role;
import com.newgen.hrm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RoleService {
    private static final String SUCCESS = "success";
    private static final String VALUE = "Data save successfully !";
    @Autowired
    public RoleRepository roleRepository;

    public Map<String, String> save(Map<String,String>map) {
        try {
            Map<String, String> msgMap = new HashMap<>();
            Role role = new Role();
            role.setName(map.get("name"));
            roleRepository.save(role);
            msgMap.put(SUCCESS, VALUE);
            return msgMap;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
