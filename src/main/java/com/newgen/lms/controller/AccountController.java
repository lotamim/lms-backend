package com.newgen.lms.controller;


import com.newgen.lms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/saveOrUpdate")
    public Map saveOrUpdate(@RequestBody Map<String,String> dMap) {
        return accountService.saveOrUpdate(dMap);
    }

    @PostMapping("/delete")
    public Map delete(@RequestBody Map<String,String> dMap) {
        return accountService.delete(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return accountService.list();
    }
}
