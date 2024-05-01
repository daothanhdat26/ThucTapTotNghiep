package com.tttn.ThucTapTotNghiep.accountservice.controller;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.accountservice.model.Role;
import com.tttn.ThucTapTotNghiep.accountservice.service.AccountDetailService;
import com.tttn.ThucTapTotNghiep.accountservice.service.AccountService;
import com.tttn.ThucTapTotNghiep.accountservice.wrapper.StudentAccountDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    AccountService accountService;


    @GetMapping
    public ResponseEntity<List<Account>> showAccount() {
        return ResponseEntity.ok().body(accountService.findAll());
    }
    //Han che su dung, khong co enCode password
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account ac) {
        ac.setType(Role.GV);
        Account account = accountService.save(ac);
        return ResponseEntity.ok(account);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHAccountById(@PathVariable Integer id) {
        try {
            accountService.deleteById(id);
            return ResponseEntity.ok("Đã xóa tài khoản có id là " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi trong quá trình xóa tài khoản");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable Integer id, @RequestBody Account account) {
        try {

            Account ac = accountService.updateAc(id, account);
            return ResponseEntity.ok("Đã sửa tài khoản có id là " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi trong quá trình cập nhật tài khoản");
        }
    }

    @PostMapping("/class/{classId}/excel")
    public String importAccountFromExcel(@PathVariable Integer classId,@RequestParam("file") MultipartFile multipartFile) {
        return accountService.importAccoutFromExcel(classId,multipartFile);
    }

}
