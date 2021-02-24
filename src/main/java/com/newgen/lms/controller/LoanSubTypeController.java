package com.newgen.lms.controller;

import com.newgen.lms.service.LoanSubTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/loanSubType")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoanSubTypeController {

    @Autowired
    private LoanSubTypeService loanSubTypeService;

    @PostMapping("/saveOrUpdate")
    public Map<String, String> save(@RequestBody Map<String, String> dMap) {
        return loanSubTypeService.save(dMap);
    }

    @PostMapping("/delete")
    public Map<String, String> delete(@RequestBody Map<String, String> dMap) {
        return loanSubTypeService.delete(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return loanSubTypeService.list();
    }
}
