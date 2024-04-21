package com.tttn.ThucTapTotNghiep.subjectclassservice.model;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
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

    @Column(name = "created_by")
    private int created_by;

    @Column(name ="created_at")
    private Timestamp created_at;
    @Column(name="school_year")
    private String school_year;

    @Column(name="number_of_group")
    private int number_of_group;

    @Column(name="member_per_group")
    private int member_per_group;

    @Column(name="group_register_method")
    private String group_register_method;


}
