package com.tttn.ThucTapTotNghiep.accountservice.controller;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> showAccount() {
        return ResponseEntity.ok().body(accountService.findAll());
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account ac) {
        Account account = accountService.save(ac);
        return ResponseEntity.ok(account);
    }

    //    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteHAccountById(@PathVariable Integer id) {
//        accountService.deleteById(id);
//        return ResponseEntity.ok("Da xoa tai khoan voi id la" + id);
//
//
//    }
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
//            account.setUser_id(id);
//            accountService.save(account);
            Account ac = accountService.updateAc(id, account);
            return ResponseEntity.ok("Đã sửa tài khoản có id là " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi trong quá trình cập nhật tài khoản");
        }
    }

}
