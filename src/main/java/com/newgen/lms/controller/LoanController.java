package com.newgen.lms.controller;


import com.newgen.lms.service.LoanService;
import com.newgen.lms.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/loan")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/saveOrUpdate")
    public Map<String, String> save(@RequestBody Map<String, String> dMap) {
        return loanService.save(dMap);
    }

    @PostMapping("/delete")
    public Map<String, String> delete(@RequestBody Map<String, String> dMap) {
        return loanService.delete(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return loanService.list();
    }

}
