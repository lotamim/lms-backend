package com.newgen.lms.controller;
/*
Created By : Mehedi Hasan Tamim
Time : 09/01/2021 08:10 PM
*/

import com.newgen.lms.common.JasperReportsService;
import com.newgen.lms.model.Role;
import com.newgen.lms.repository.RoleRepository;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ApplicationContext context;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JasperReportsService jasperReportsService;

    @GetMapping(path = "/roleReport")
    public ResponseEntity<byte[]> getPdf() throws JRException {
        String username = "Mehedi Hasan";
        String reportName = "roleReport";
        List<Role> roles = roleRepository.findAll();
        JRDataSource dataSource = new JRBeanCollectionDataSource(roles);
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        byte[] bytes = jasperReportsService.generatePDFReport("roleReport", params, dataSource);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE))
                .body(bytes);
    }

}
