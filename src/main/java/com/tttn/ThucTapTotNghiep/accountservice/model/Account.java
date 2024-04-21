package com.tttn.ThucTapTotNghiep.accountservice.model;

import jakarta.persistence.*;
import lombok.*;




@Entity
@Table(name = "account")
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

    public Account() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

}
