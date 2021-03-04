package com.newgen.lms.controller;

import com.newgen.lms.service.SanctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sanction")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SanctionController {

    @Autowired
    private SanctionService sanctionService;

    @PostMapping("/save")
    public Map save(@RequestBody Map<String, Object> dMap) {
        return sanctionService.save(dMap);
    }

    @PostMapping("/update")
    public Map update(@RequestBody Map<String, String> dMap) {
        return sanctionService.update();
    }

    @PostMapping("/delete")
    public Map delete(@RequestBody Map<String, String> dMap) {
        return sanctionService.delete();
    }

    @GetMapping("/list")
    public Map list() {
        return sanctionService.list();
    }
}
