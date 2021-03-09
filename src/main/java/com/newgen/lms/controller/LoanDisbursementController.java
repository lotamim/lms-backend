package com.newgen.lms.controller;

import com.newgen.lms.service.LoanDisbursementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/loanDisbursement")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoanDisbursementController {

    @Autowired
    private LoanDisbursementService loanDisbursementService;

    @PostMapping("/save")
    public Map save(@RequestBody Map<String, String> dMap) {
        return loanDisbursementService.save(dMap);
    }

    @PostMapping("/update")
    public Map update(@RequestBody Map<String, String> dMap) {
        return loanDisbursementService.update();
    }

    @PostMapping("/delete")
    public Map delete(@RequestBody Map<String, String> dMap) {
        return loanDisbursementService.delete();
    }

    @GetMapping("/list")
    public Map list() {
        return loanDisbursementService.list();
    }
}
