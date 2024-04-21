package com.tttn.ThucTapTotNghiep.accountservice.model;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;

    @Column(name ="user_password")
    private String user_password;
    @Column(name="user_email")
    private String user_email;
    @Column(name="user_type")
    private  String user_type;
    @Column(name ="phone_number")
    private String phone_number ;

    @Column(name = "full_name")
    private String full_name;

    public Account(String user_password, String user_email, String user_type, String full_name) {
        this.user_password = user_password;
        this.user_email = user_email;
        this.user_type = user_type;
        this.full_name = full_name;
    }
}
