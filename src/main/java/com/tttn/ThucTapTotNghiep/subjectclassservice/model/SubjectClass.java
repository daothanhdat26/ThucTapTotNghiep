package com.tttn.ThucTapTotNghiep.subjectclassservice.model;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class SubjectClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_class_id")
    private int subject_class_id;

    @Column(name ="subject_name")
    private String subject_name;

    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "user_id")
    private Account created_by;

    @Column(name ="created_at")
    private Date created_at;
    @Column(name="school_year")
    private String school_year;

    @Column(name="number_of_group")
    private int number_of_group;

    @Column(name="member_per_group")
    private int member_per_group;

    @Column(name="group_register_method")
    private String group_register_method;

    public int getSubject_class_id() {
        return subject_class_id;
    }

    public void setSubject_class_id(int subject_class_id) {
        this.subject_class_id = subject_class_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public Account getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Account created_by) {
        this.created_by = created_by;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getSchool_year() {
        return school_year;
    }

    public void setSchool_year(String school_year) {
        this.school_year = school_year;
    }

    public int getNumber_of_group() {
        return number_of_group;
    }

    public void setNumber_of_group(int number_of_group) {
        this.number_of_group = number_of_group;
    }

    public int getMember_per_group() {
        return member_per_group;
    }

    public void setMember_per_group(int member_per_group) {
        this.member_per_group = member_per_group;
    }

    public String getGroup_register_method() {
        return group_register_method;
    }

    public void setGroup_register_method(String group_register_method) {
        this.group_register_method = group_register_method;
    }
}
