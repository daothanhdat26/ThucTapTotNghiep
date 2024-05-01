package com.tttn.ThucTapTotNghiep.securityService.service;


import com.tttn.ThucTapTotNghiep.securityService.entity.User;
import com.tttn.ThucTapTotNghiep.securityService.model.AuthenticationResponse;
import com.tttn.ThucTapTotNghiep.securityService.model.LoginRequest;
import com.tttn.ThucTapTotNghiep.securityService.model.RegisterRequest;
import com.tttn.ThucTapTotNghiep.securityService.model.Role;
import com.tttn.ThucTapTotNghiep.securityService.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepo, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    public AuthenticationResponse register(RegisterRequest request){
        User user=new User();
        user.setFullname(request.getFullname());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        user.setPhone(request.getPhone());

        user.setRole(Role.GV);

        userRepo.save(user);
        String token=jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
//        User user=userRepo.findByUsername(request.getUsername()).orElseThrow();
//        String token=jwtService.generateToken(user);
//        return new AuthenticationResponse(token);
        var user=userRepo.findByUsername(request.getUsername()).orElseThrow();
        var token=jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}