package com.newgen.lms.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sanction")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SanctionController {
    @PostMapping("/save")
    public Map save(@RequestBody Map<String, Object> dMap) {
        return null;
    }

    @PostMapping("/update")
    public Map update(@RequestBody Map<String, Object> dMap) {
        return null;
    }

    @PostMapping("/delete")
    public Map delete(@RequestBody Map<String, String> dMap) {
        return null;
    }

    @GetMapping("/list")
    public Map list() {
        return null;
    }
}
