package com.newgen.lms.controller;


import com.newgen.lms.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/menuItem")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @PostMapping("/save")
    public Map save(@RequestBody Map<String, String> dMap) {
        return menuItemService.save(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return menuItemService.getList();
    }

    @GetMapping("/dynamicMenuItem")
    public Map getDynamicMenuItem(){
        return menuItemService.getDynamicMenuItem();
    }

}
