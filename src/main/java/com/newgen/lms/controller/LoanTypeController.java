package com.newgen.lms.controller;


import com.newgen.lms.service.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/loanType")
public class LoanTypeController {

    @Autowired
    private LoanTypeService loanTypeService;

    @PostMapping("/saveOrUpdate")
    public Map<String, String> save(@RequestBody Map<String, String> dMap) {
        return loanTypeService.save(dMap);
    }

    @PostMapping("/delete")
    public Map<String, String> delete(@RequestBody Map<String, String> dMap) {
        return loanTypeService.delete(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return loanTypeService.list();
    }
}
