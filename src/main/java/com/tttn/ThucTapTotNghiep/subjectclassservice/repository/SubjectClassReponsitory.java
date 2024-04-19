package com.tttn.ThucTapTotNghiep.subjectclassservice.repository;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.subjectclassservice.model.SubjectClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectClassReponsitory extends JpaRepository<SubjectClass,Integer> {

}
