package com.newgen.lms.controller;

import com.newgen.lms.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/charge")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    @PostMapping("/saveOrUpdate")
    public Map saveOrUpdate(@RequestBody Map<String, String> dMap) {
        return chargeService.saveOrUpdate(dMap);
    }

    @PostMapping("/delete")
    public Map delete(@RequestBody Map<String, String> dMap) {
        return chargeService.delete(dMap);
    }

    @GetMapping("/list")
    public Map list() {
        return chargeService.list();
    }

}
