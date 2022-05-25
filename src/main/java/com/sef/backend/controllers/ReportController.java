package com.sef.backend.controllers;

import com.sef.backend.models.ReportModel;
import com.sef.backend.services.IReportService;
import com.sef.backend.services.ReportService;

public class ReportController {
    private final IReportService reportService = new ReportService();

    public int addReport(ReportModel reportModel){
        return reportService.addReport(reportModel);
    }
}
