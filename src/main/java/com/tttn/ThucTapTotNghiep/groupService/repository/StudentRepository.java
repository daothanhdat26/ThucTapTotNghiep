package com.tttn.ThucTapTotNghiep.groupService.repository;

import com.tttn.ThucTapTotNghiep.groupService.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    public List<Student>getStudentsByClassId(Integer classId);
    public List<Student>getStudentsByStudentId(Integer studentId);
    List<Student> findByStudentId(Integer studentId);
    void deleteByClassIdAndStudentId(int classId, int studentId);

}
