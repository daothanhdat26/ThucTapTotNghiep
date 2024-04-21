package com.tttn.ThucTapTotNghiep.subjectclassservice.service;



import com.tttn.ThucTapTotNghiep.subjectclassservice.model.SubjectClass;
import com.tttn.ThucTapTotNghiep.subjectclassservice.repository.SubjectClassReponsitory;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class SubjectClassService  {
    SubjectClassReponsitory subjectClassReponsitory;



    public Optional<SubjectClass> findById(Integer integer) {
        return subjectClassReponsitory.findById(integer);
    }

    public List<SubjectClass> findAll() {
        return subjectClassReponsitory.findAll();
    }


    public SubjectClass save(SubjectClass sc) {
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        sc.setCreated_at(timestamp);
        return subjectClassReponsitory.save(sc);
    }

    public void deleteById(Integer id) {
        subjectClassReponsitory.deleteById(id);
    }


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
