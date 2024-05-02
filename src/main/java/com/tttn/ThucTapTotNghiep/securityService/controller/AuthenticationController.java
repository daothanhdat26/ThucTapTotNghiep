package com.tttn.ThucTapTotNghiep.securityService.controller;


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
import org.springframework.web.bind.annotation.RequestHeader;

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
    @GetMapping("/api/authenticate/userId")
    public ResponseEntity<String>getUserIdByToken(@RequestHeader(value = "Authorization")String token){
        Integer id=authenticationService.getUserIdFromToken(token);
        return new ResponseEntity<String>("UserId:"+id,HttpStatus.OK);
    }
}
