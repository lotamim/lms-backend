package com.newgen.hrm.controller;

import com.newgen.hrm.common.JasperReportsService;
import com.newgen.hrm.model.Role;
import com.newgen.hrm.repository.RoleRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
Created By : Mehedi Hasan Tamim
Time : 09/01/2021 08:10 PM
*/
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ApplicationContext context;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private JasperReportsService jasperReportsService;

    @GetMapping(path = "/pdf")
    public ResponseEntity<byte[]> getPdf() throws IOException, JRException {
        String username = "Mehedi Hasan Tamim";
        String reportName = "roleReport";
        List<Role> roles =  roleRepository.findAll();
        JRDataSource dataSource = new JRBeanCollectionDataSource(roles);
        Map<String, Object> params = new HashMap<>();

        params.put("userName", username);
        byte[] bytes = jasperReportsService.generatePDFReport("roleReport", params, dataSource);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(MediaType.APPLICATION_PDF_VALUE))
                .body(bytes);
    }

}
