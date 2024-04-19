package com.tttn.ThucTapTotNghiep.subjectclassservice.service;


import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.subjectclassservice.model.SubjectClass;
import com.tttn.ThucTapTotNghiep.subjectclassservice.repository.SubjectClassReponsitory;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SubjectClassImpl implements SubjectClassService {
     private SubjectClassReponsitory subjectClassReponsitory;

    @Override
    public List<SubjectClass> findAll() {
        return subjectClassReponsitory.findAll();
    }

    @Override
    public SubjectClass save(SubjectClass sc) {
        return subjectClassReponsitory.save(sc);
    }

    @Override
    public void deleteById(Integer id) {
        subjectClassReponsitory.deleteById(id);
    }

//    @Override
//    public Account updateSc(Integer id, SubjectClass subjectClass){
//        SubjectClass sc = subjectClassReponsitory.findById(id).orElseThrow(() -> new EntityNotFoundException("Khong co lop hoc voi id:"+id));
//        sc.setSubject_name(subjectClass.getSubject_name());
//
//
//
//
//    }

}
