package com.tttn.ThucTapTotNghiep.securityService.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private String fullname;
    private String phone;
}
