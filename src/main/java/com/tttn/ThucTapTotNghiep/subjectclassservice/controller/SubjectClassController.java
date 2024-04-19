package com.tttn.ThucTapTotNghiep.subjectclassservice.controller;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.accountservice.service.AccountService;
import com.tttn.ThucTapTotNghiep.subjectclassservice.model.SubjectClass;
import com.tttn.ThucTapTotNghiep.subjectclassservice.service.SubjectClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/class")
public class SubjectClassController {
    @Autowired
    private SubjectClassService subjectClassService;
    @GetMapping
    public ResponseEntity<List<SubjectClass>> showSubjectClass() {
        return ResponseEntity.ok().body(subjectClassService.findAll());
    }
    @PostMapping
    public ResponseEntity<SubjectClass> createSubjectClass(@RequestBody SubjectClass sc) {
        SubjectClass subjectClass = subjectClassService.save(sc);
        return ResponseEntity.ok(subjectClass);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubjectClassyId(@PathVariable Integer id) {
        try {
            subjectClassService.deleteById(id);
            return ResponseEntity.ok("Đã xóa lớp môn học có id là " + id);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi trong quá trình xóa");
        }
    }

}
