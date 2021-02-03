package com.newgen.lms.controller;

import com.newgen.lms.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/userPermission")
public class UserPermissionController {

    @Autowired
    private UserPermissionService userPermissionService;

    @PostMapping("/getPermissionList")
    public Map getPermissionListForRole(@RequestBody Map<String, String> dMap) {
        return userPermissionService.getPermissionListForRole(dMap.get("roleId"));
    }

    @PostMapping("/save")
    public Map save(@RequestParam Map<String, String> dMap) {
        return userPermissionService.save(dMap);
    }
}
