package com.newgen.lms.controller;

import com.newgen.lms.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/save")
    public Map<String, String> save(@RequestBody Map<String, String> dMap) {
        return menuService.save(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return menuService.list();
    }

}
