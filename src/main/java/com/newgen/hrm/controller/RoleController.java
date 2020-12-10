package com.newgen.hrm.controller;

import com.newgen.hrm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/role")
public class RoleController {
    @Autowired
    public RoleService roleService;

    @PostMapping(value = "/save")
    public Map<String, String> save(@RequestBody Map<String,String> reqMap) {
        return roleService.save(reqMap);
    }
}
