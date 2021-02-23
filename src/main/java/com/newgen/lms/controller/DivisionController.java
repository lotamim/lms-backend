package com.newgen.lms.controller;

import com.newgen.lms.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/division")
@CrossOrigin(origins = "*", maxAge = 3600)
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    @PostMapping("/saveOrUpdate")
    public Map<String, String> save(@RequestBody Map<String, String> dMap) {
        return divisionService.save(dMap);
    }

    @PostMapping("/delete")
    public Map<String, String> delete(@RequestBody Map<String, String> dMap) {
        return divisionService.delete(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return divisionService.list();
    }

}
