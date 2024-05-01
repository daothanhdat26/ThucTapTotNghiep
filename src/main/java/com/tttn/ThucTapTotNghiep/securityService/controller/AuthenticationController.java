package com.tttn.ThucTapTotNghiep.securityService.controller;


import com.tttn.ThucTapTotNghiep.securityService.entity.User;
import com.tttn.ThucTapTotNghiep.securityService.model.AuthenticationResponse;
import com.tttn.ThucTapTotNghiep.securityService.model.LoginRequest;
import com.tttn.ThucTapTotNghiep.securityService.model.RegisterRequest;
import com.tttn.ThucTapTotNghiep.securityService.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;

    }
    @PostMapping("/api/authenticate/register")
    public ResponseEntity<AuthenticationResponse>register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
    }
    @PostMapping("/api/authenticate/login")
    public ResponseEntity<AuthenticationResponse>login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @GetMapping("/api/test/token")
    public ResponseEntity<String>testToken(){
        return new ResponseEntity<String>("YOUR LOGGED",HttpStatus.OK);
    }
}
