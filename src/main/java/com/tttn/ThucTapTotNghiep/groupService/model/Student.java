package com.tttn.ThucTapTotNghiep.groupService.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "student_list")
public class Student {

    @Id
    @Column(name = "student_index")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentIndex;
    @Column(name = "class_id")
    private int classId;
    @Column(name = "studentId")
    private int studentId;

    public Student(int classId, int studentId) {
        this.classId = classId;
        this.studentId = studentId;
    }
}
