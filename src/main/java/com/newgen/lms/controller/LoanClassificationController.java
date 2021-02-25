package com.newgen.lms.controller;

import com.newgen.lms.service.LoanClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/loanClassification")
@CrossOrigin(origins = "*", maxAge = 3600)
public class LoanClassificationController {

    @Autowired
    private LoanClassificationService loanClassificationService;

    @PostMapping("/saveOrUpdate")
    public Map saveOrUpdate(@RequestBody Map<String, String> dMap) {
        return loanClassificationService.saveOrUpdate(dMap);
    }

    @PostMapping("/delete")
    public Map delete(@RequestBody Map<String, String> dMap) {
        return loanClassificationService.delete(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return loanClassificationService.list();
    }


}
