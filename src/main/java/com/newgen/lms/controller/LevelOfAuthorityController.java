package com.newgen.lms.controller;

import com.newgen.lms.model.Sanction;
import com.newgen.lms.service.LevelOfAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/levelOfAuthority")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LevelOfAuthorityController {
    @Autowired
    private LevelOfAuthorityService levelOfAuthorityService;

    @PostMapping("/save")
    public Map save(@RequestBody Map<String,String> dMap) {
        return levelOfAuthorityService.save(dMap);
    }

    @GetMapping("/list")

    public Map list() {
        return null;
    }

    @PostMapping("/update")
    public Map update() {
        return null;
    }

    @PostMapping("/delete")
    public Map delete() {
        return null;
    }
}
