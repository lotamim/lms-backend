package com.newgen.lms.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sanction")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SanctionController {
    @PostMapping("/saveOrUpdate")
    public Map saveOrUpdate(@RequestBody Map<String, String> dMap) {
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
