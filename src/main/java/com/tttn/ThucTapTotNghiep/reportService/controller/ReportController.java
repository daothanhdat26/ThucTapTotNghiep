package com.tttn.ThucTapTotNghiep.reportService.controller;

import com.tttn.ThucTapTotNghiep.projectsService.model.Project;
import com.tttn.ThucTapTotNghiep.reportService.model.ReportRequest;
import com.tttn.ThucTapTotNghiep.reportService.model.ReportSubmit;
import com.tttn.ThucTapTotNghiep.reportService.service.ReportService;
import com.tttn.ThucTapTotNghiep.reportService.wrapper.ReportForm;
import com.tttn.ThucTapTotNghiep.reportService.wrapper.SubmitForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }
    //tao bao cao(gv)
    @PostMapping("/api/report-request")
    public ResponseEntity<String>createReportRequest(@RequestBody ReportRequest reportRequest, @RequestHeader(value = "Authorization") String requestToken){
        ReportRequest savedReportRequest = reportService.saveReportRequest(reportRequest, requestToken);
        if (savedReportRequest != null) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("FAILED", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // xoa bao cao(gv)
    @DeleteMapping("/api/report-request/{requestId}")
    public ResponseEntity<String>deleteReportRequest(@PathVariable Integer requestId){
        return ResponseEntity.ok(reportService.deleteReportRequest(requestId));
    }
    // sua bao cao(gv)
    @PutMapping("/api/report-request/{requestId}")
    public ResponseEntity<String>updateReportRequest(@PathVariable Integer requestId, @RequestBody ReportForm reportRequest){
        return ResponseEntity.ok(reportService.updateReportRequest(requestId,reportRequest));
    }
    // lay tat ca bao cao(gv)
    @GetMapping("/api/report-request")
    public List<ReportRequest> getAllReportRequest(){
        return reportService.getAllReportRequest();
    }
    //lay danh sach bao cao theo classId
    @GetMapping("/api/report-request/{classId}")
    public  List<ReportRequest> getReportRequestsBySubjectClass(@PathVariable Integer classId){
        return reportService.getReportRequestsBySubjectClass(classId);
    }
    // nop bao cao
//    @PostMapping("/api/report-submit/{requestId}")
//    public ResponseEntity<String>createReportSubmit(@PathVariable Integer requestId  ,@RequestBody ReportSubmit reportsubmit, @RequestHeader(value = "Authorization") String requestToken){
//        return ResponseEntity.ok(reportService.saveReportSubmit(requestId,reportsubmit,requestToken));
//    }
//    @PostMapping("/api/report-submit/{requestId}")
//    public ResponseEntity<String> saveReportSubmit(@PathVariable Integer requestId,
//                                                   @RequestParam("reportTitle") String reportTitle,
//                                                   @RequestParam("reportDescription") String reportDescription,
//                                                   @RequestParam("attachment") MultipartFile attachment,
//                                                   @RequestHeader(value = "Authorization") String requestToken) {
//        ReportSubmit reportSubmit = new ReportSubmit();
//        reportSubmit.setReportTitle(reportTitle);
//        reportSubmit.setReportDescription(reportDescription);
//        String result = reportService.saveReportSubmit(requestId, reportSubmit, attachment, requestToken);
//        return ResponseEntity.ok().body(result);
//    }
    @PostMapping("/api/report-submit/{requestId}/{reportTitle}/{reportDescription}")
    public ResponseEntity<String> saveReportSubmit(
            @PathVariable Integer requestId,
            @PathVariable String reportTitle,
            @PathVariable String reportDescription,
            @RequestParam("attachment") MultipartFile attachment,
            @RequestHeader(value = "Authorization") String requestToken) {

        String result = reportService.saveReportSubmit(requestId, reportTitle, reportDescription, attachment, requestToken);
        return ResponseEntity.ok().body(result);
    }



}
