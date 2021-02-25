package com.newgen.lms.controller;

import com.newgen.lms.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bank")
@CrossOrigin(origins = "*",maxAge = 3600)
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/saveOrUpdate")
    public Map<String, String> save(@RequestBody Map<String, String> dMap) {
        return bankService.save(dMap);
    }

    @PostMapping("/delete")
    public Map<String, String> delete(@RequestBody Map<String, String> dMap) {
        return bankService.delete(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return bankService.list();
    }

}
