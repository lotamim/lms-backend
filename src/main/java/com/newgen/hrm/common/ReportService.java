package com.newgen.hrm.common;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.util.Map;

public interface ReportService {
    /**
     * Generates a HTML report with the given input file
     *
     * @param reportName
     *            report source file without extension
     * @param params
     *            report parameters
     * @param dataSource
     *            the source of data
     * @return the byte[] containing the PDF
     */
    byte[] generatePDFReport(String reportName, Map<String, Object> params, JRDataSource dataSource) throws IOException, JRException;
}
