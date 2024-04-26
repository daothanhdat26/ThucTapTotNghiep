package com.tttn.ThucTapTotNghiep.accountservice.controller;

import com.tttn.ThucTapTotNghiep.accountservice.service.AccountDetailService;
import com.tttn.ThucTapTotNghiep.accountservice.wrapper.StudentAccountDetail;
import com.tttn.ThucTapTotNghiep.groupService.wrapper.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class AccountDetailController {
    @Autowired
    AccountDetailService accountDetailService;


    @GetMapping("/api/account/{accountId}/student-detail")
    public StudentAccountDetail getStudentAccountDetail(@PathVariable Integer accountId){
        return accountDetailService.getStudentAccountDetail(accountId);
    }
    @GetMapping("/api/class/{classId}/student-list")
    public List<StudentInfo>getStudentListOfClass(@PathVariable Integer classId){
        return accountDetailService.getStudentInfoOfClass(classId);
    }
}
