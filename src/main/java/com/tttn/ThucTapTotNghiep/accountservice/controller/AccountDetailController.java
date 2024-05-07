package com.tttn.ThucTapTotNghiep.accountservice.controller;

import com.tttn.ThucTapTotNghiep.accountservice.service.AccountDetailService;
import com.tttn.ThucTapTotNghiep.accountservice.wrapper.StudentAccountDetail;
import com.tttn.ThucTapTotNghiep.groupService.wrapper.StudentInfo;
import com.tttn.ThucTapTotNghiep.securityService.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class AccountDetailController {
    AccountDetailService accountDetailService;
    AuthenticationService authenticationService;

    public AccountDetailController(AccountDetailService accountDetailService, AuthenticationService authenticationService) {
        this.accountDetailService = accountDetailService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/api/account/{accountId}/student-detail")
    public StudentAccountDetail getStudentAccountDetail(@PathVariable Integer accountId){
        return accountDetailService.getStudentAccountDetail(accountId);
    }
    @GetMapping("/api/class/{classId}/student-list")
    public List<StudentInfo>getStudentListOfClass(@PathVariable Integer classId){
        return accountDetailService.getStudentInfoOfClass(classId);
    }
    @GetMapping("/api/account/token-detail")
    public ResponseEntity<?>getAccountInfoByToken(@RequestHeader(value = "Authorization")String token){
        int userId=authenticationService.getUserIdFromToken(token);
        return accountDetailService.getAccountDetail(userId);
    }
}
