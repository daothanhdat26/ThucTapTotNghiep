package com.tttn.ThucTapTotNghiep.subjectclassservice.service;


import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.subjectclassservice.model.SubjectClass;
import com.tttn.ThucTapTotNghiep.subjectclassservice.repository.SubjectClassReponsitory;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());

        SubjectClass subjectClass=new SubjectClass();

        subjectClass.setCreated_at(timestamp);

        subjectClass.setSubject_name(sc.getSubject_name());
        subjectClass.setCreated_by(sc.getCreated_by());
        subjectClass.setSchool_year(sc.getSchool_year());
        subjectClass.setGroup_register_method(sc.getGroup_register_method());
        subjectClass.setNumber_of_group(sc.getNumber_of_group());
        subjectClass.setMember_per_group(sc.getMember_per_group());




        return subjectClassReponsitory.save(subjectClass);
    }

    @Override
    public void deleteById(Integer id) {
        subjectClassReponsitory.deleteById(id);
    }

    @Override
    public SubjectClass updateSc(Integer id, SubjectClass subjectClass){
        SubjectClass sc = subjectClassReponsitory.findById(id).orElseThrow(() -> new EntityNotFoundException("Khong co lop hoc voi id:"+id));
        sc.setSubject_name(subjectClass.getSubject_name());

        sc.setCreated_by(subjectClass.getCreated_by());


        sc.setCreated_at(subjectClass.getCreated_at());
        sc.setSchool_year(subjectClass.getSchool_year());
        sc.setNumber_of_group(subjectClass.getNumber_of_group());
        sc.setMember_per_group(subjectClass.getMember_per_group());
        sc.setGroup_register_method(subjectClass.getGroup_register_method());
        subjectClassReponsitory.save(sc);
        return sc;
    }

}
