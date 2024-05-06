package com.tttn.ThucTapTotNghiep.reportService.service;


import com.tttn.ThucTapTotNghiep.reportService.respository.ReportSubmitRepository;
import com.tttn.ThucTapTotNghiep.reportService.respository.ReportRequestRepository;
import com.tttn.ThucTapTotNghiep.reportService.wrapper.ReportForm;
import com.tttn.ThucTapTotNghiep.securityService.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    private final ReportSubmitRepository reportSubmitRepository;
    private final ReportRequestRepository reportRequestRepository;
    private final AuthenticationService authenticationService;
    public ReportService(ReportSubmitRepository reportSubmitRepository, ReportRequestRepository reportRequestRepository, AuthenticationService authenticationService) {
        this.reportSubmitRepository = reportSubmitRepository;
        this.reportRequestRepository = reportRequestRepository;
        this.authenticationService = authenticationService;
    }
    public ResponseEntity<?>saveReportRequest(ReportForm formData,String requestToken){
        
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
