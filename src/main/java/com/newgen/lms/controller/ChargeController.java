package com.newgen.lms.controller;


import com.newgen.lms.service.ChargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/charge")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ChargeController {

    @Autowired
    private ChargeService chargeService;

    public Map saveOrUpdate(@RequestBody Map<String, String> dMap) {
        return chargeService.saveOrUpdate(dMap);
    }

    public Map delete(@RequestBody Map<String, String> dMap) {
        return chargeService.delete(dMap);
    }

    public Map list() {
        return chargeService.list();
    }
}
