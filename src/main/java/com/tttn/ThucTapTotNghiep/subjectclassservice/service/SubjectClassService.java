package com.tttn.ThucTapTotNghiep.subjectclassservice.service;

import com.tttn.ThucTapTotNghiep.subjectclassservice.model.SubjectClass;

import java.util.List;

public interface SubjectClassService {

    List<SubjectClass> findAll();

    SubjectClass save(SubjectClass sc);

    void deleteById(Integer integer);
    SubjectClass updateSc(Integer id ,SubjectClass ac);
}
