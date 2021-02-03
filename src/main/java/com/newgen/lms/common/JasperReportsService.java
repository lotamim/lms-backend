package com.newgen.lms.common;
/*
 @author:Mehedi Hasan Tamim
 Note : Please not modified it without my concern :: Or your line manager
 Modified By : ,,
 Created Date :
*/

import net.sf.jasperreports.engine.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Map;

@Component
public class JasperReportsService implements ReportService {
    @Override
    public byte[] generatePDFReport(String reportName, Map<String, Object> params, JRDataSource dataSource) {
        byte[] bytes = null;
        Map<String, Object> dMap = params;
        try {

            //Get Jasper template from resources folder
            File file = new File(Constants.SUB_DIR_PATH);
            final String MAIN_REPORT = file.getAbsolutePath() + File.separator + "reports/" + reportName + ".jasper";
            final String SUB_DIR_PATH = file.getAbsolutePath() + File.separator + "reports/";
            final String COMPANY_LOGO = file.getAbsolutePath() + File.separator + "reports/logo/babylon.jpeg";

            // Modified Params
            dMap.put("SUB_DIR", SUB_DIR_PATH);
            dMap.put("company_logo", COMPANY_LOGO);

            // Print Report
            JasperPrint jasperPrint = JasperFillManager.fillReport(MAIN_REPORT, dMap, dataSource);

            //Export PDF Stream
            bytes = JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            e.printStackTrace();
        }
        return bytes;
    }
}
