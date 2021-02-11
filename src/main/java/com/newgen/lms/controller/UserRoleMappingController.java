package com.newgen.lms.controller;

import com.newgen.lms.service.UserRoleMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/userRoleMapping")
public class UserRoleMappingController {
    @Autowired
    private UserRoleMappingService userRoleMappingService;

    @PostMapping("/save")
    public Map<String, String> save(@RequestParam Map<String, String> dMap) {
        return userRoleMappingService.save(dMap);
    }

    @GetMapping("/list")
    public List<Map<?, ?>> getUserRoleMappingList() {
        return userRoleMappingService.getRoleMappingList();
    }

    @PostMapping("/update")
    public Map<String, String> update(@RequestParam Map<String, String> dMap) {
        return userRoleMappingService.update(dMap);
    }
}
