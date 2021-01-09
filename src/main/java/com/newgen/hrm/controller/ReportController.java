package com.newgen.hrm.controller;

import com.newgen.hrm.model.Role;
import com.newgen.hrm.repository.RoleRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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

    @GetMapping(path = "/pdf")
    @ResponseBody
    public void getPdf(HttpServletResponse response) throws Exception {
        //Get JRXML template from resources folder
//        Resource resource = context.getResource("classpath:reports/" + jrxml + ".jrxml");
        Resource resource = context.getResource("classpath:reports/role_List.jrxml");
        //Compile to jasperReport
        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);
        //Parameters Set
        Map<String, Object> params = new HashMap<>();

        List<Role> roles = (List<Role>) roleRepository.findAll();

        //Data source Set
        JRDataSource dataSource = new JRBeanCollectionDataSource(roles);
        params.put("datasource", dataSource);

        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        //Media Type
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        //Export PDF Stream
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
