package com.newgen.hrm.controller;

import com.newgen.hrm.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/save")
    public Map save(@RequestParam(value = "organizationLogo", required = false) MultipartFile file,
                    @RequestParam Map<String, String> dMap) {
        return organizationService.save(file, dMap);
    }

    @GetMapping("/list")
    public Map orgList() {
        return organizationService.list();
    }

    @PostMapping ("/select")
    public Map select (@RequestBody Map<String,String>dMap){
        return organizationService.select(dMap);
    }
}
