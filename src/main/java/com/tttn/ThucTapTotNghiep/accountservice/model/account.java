package com.tttn.ThucTapTotNghiep.accountservice.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userPassword;
    private String userEmail;
    private String userType;
    private String phoneNumber;
    private String fullName;

}
