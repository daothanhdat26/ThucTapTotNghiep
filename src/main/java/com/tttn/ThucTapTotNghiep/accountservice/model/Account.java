package com.tttn.ThucTapTotNghiep.accountservice.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

}
