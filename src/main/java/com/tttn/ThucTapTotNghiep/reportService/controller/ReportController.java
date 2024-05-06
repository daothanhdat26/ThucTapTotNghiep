package com.tttn.ThucTapTotNghiep.reportService.controller;

import com.tttn.ThucTapTotNghiep.reportService.service.ReportService;
import com.tttn.ThucTapTotNghiep.reportService.wrapper.ReportForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    @PostMapping("/api/report/make-request")
    public ResponseEntity<String>createReportRequest(@RequestBody ReportForm formData){
        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }
}
