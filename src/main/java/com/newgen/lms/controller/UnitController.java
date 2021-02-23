package com.newgen.lms.controller;

import com.newgen.lms.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/unit")
@CrossOrigin(origins = "*", maxAge = 3600)
public class UnitController {

    @Autowired
    private UnitService unitService;

    @PostMapping("/saveOrUpdate")
    public Map<String, String> save(@RequestBody Map<String, String> dMap) {
        return unitService.save(dMap);
    }

    @PostMapping("/delete")
    public Map<String, String> delete(@RequestBody Map<String, String> dMap) {
        return unitService.delete(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return unitService.list();
    }

}
