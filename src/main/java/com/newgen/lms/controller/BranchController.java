package com.newgen.lms.controller;

import com.newgen.lms.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/branch")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BranchController {
    @Autowired
    private BranchService branchService;

    @PostMapping("/save")
    public Map save(@RequestBody Map<String, String> dMap) {
        return branchService.save(dMap);
    }

    @PostMapping("/delete")
    public Map delete(@RequestBody Map<String, String> dMap) {
        return branchService.delete(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return branchService.list();
    }
}
