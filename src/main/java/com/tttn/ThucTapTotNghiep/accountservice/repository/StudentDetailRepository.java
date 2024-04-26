package com.tttn.ThucTapTotNghiep.accountservice.repository;

import com.tttn.ThucTapTotNghiep.accountservice.model.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDetailRepository extends JpaRepository<StudentDetail,Integer> {
    public StudentDetail findStudentDetailByUserId(Integer userId);
}
