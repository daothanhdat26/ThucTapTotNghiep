package com.tttn.ThucTapTotNghiep.accountservice.controller;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private  AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> showAccount() {
        return ResponseEntity.ok().body(accountService.findAll());
    }
    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account ac) {
        Account account = accountService.save(ac);
        return ResponseEntity.ok(account);
    }

}
