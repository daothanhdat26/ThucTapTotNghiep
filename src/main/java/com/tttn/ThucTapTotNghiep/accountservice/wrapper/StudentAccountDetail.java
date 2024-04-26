package com.tttn.ThucTapTotNghiep.accountservice.wrapper;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAccountDetail {
    private int accountId;
    private String fullName;
    private String studentId;
    private String studentClass;
    private String accountEmail;
    private String phoneNumber;
}
