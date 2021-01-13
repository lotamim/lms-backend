package com.newgen.hrm.common;

import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JasperReportsService implements ReportService {
    @Autowired
    ApplicationContext context;

    @Override
    public byte[] generatePDFReport(String reportName, Map<String, Object> params, JRDataSource dataSource) {
        byte[] bytes = null;
        JasperReport jasperReport = null;

        try {
            //Get JRXML template from resources folder
            Resource resource = context.getResource("classpath:reports/" + reportName + ".jrxml");
            //Compile to jasperReport
            InputStream inputStream = resource.getInputStream();
            jasperReport = JasperCompileManager.compileReport(inputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            //Export PDF Stream
            bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException | IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
