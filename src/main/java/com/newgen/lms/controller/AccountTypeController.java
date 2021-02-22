package com.newgen.lms.controller;


import com.newgen.lms.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/accountType")
public class AccountTypeController {

    @Autowired
    private AccountTypeService accountTypeService;

    @PostMapping("/saveOrUpdate")
    public Map saveOrUpdate(@RequestBody Map<String,String> dMap) {
        return accountTypeService.saveOrUpdate(dMap);
    }

    @PostMapping("/delete")
    public Map delete(@RequestBody Map<String,String> dMap) {
        return accountTypeService.delete(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return accountTypeService.list();
    }
}
