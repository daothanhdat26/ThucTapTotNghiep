package com.tttn.ThucTapTotNghiep.accountservice.service;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.accountservice.model.Role;
import com.tttn.ThucTapTotNghiep.accountservice.model.StudentDetail;
import com.tttn.ThucTapTotNghiep.accountservice.repository.AccountRepository;
import com.tttn.ThucTapTotNghiep.accountservice.repository.StudentDetailRepository;
import com.tttn.ThucTapTotNghiep.accountservice.wrapper.StudentAccountDetail;
import com.tttn.ThucTapTotNghiep.accountservice.wrapper.TeacherAccountDetail;
import com.tttn.ThucTapTotNghiep.groupService.model.Student;
import com.tttn.ThucTapTotNghiep.groupService.repository.StudentRepository;
import com.tttn.ThucTapTotNghiep.groupService.wrapper.StudentInfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class AccountDetailService {
    @Autowired
    AccountRepository accountRepository;
    StudentDetailRepository studentDetailRepository;
    StudentRepository studentRepository;

    public AccountDetailService(AccountRepository accountRepository, StudentDetailRepository studentDetailRepository, StudentRepository studentRepository) {
        this.accountRepository = accountRepository;
        this.studentDetailRepository = studentDetailRepository;
        this.studentRepository = studentRepository;
    }

    public StudentAccountDetail getStudentAccountDetail(int userId){
        Account account=accountRepository.getOne(userId);
        StudentDetail studentDetail=studentDetailRepository.getOne(userId);
        StudentAccountDetail accountDetail = new StudentAccountDetail(account.getUserId(),
                account.getFullName(),
                studentDetail.getStudentId(),
                studentDetail.getStudentClass(),
                account.getEmail(),
                account.getPhoneNumber());
        return accountDetail;
    }
    public List<StudentInfo>getStudentInfoOfClass(int classId){
        List<StudentInfo>result=new ArrayList<>();
        List<Student>studentList=studentRepository.getStudentsByClassId(classId);
        for(Student student:studentList){
            StudentInfo studentInfo=new StudentInfo();
            StudentAccountDetail accountDetail=getStudentAccountDetail(student.getStudentId());
            studentInfo.setFullName(accountDetail.getFullName());
            studentInfo.setStudentClass(accountDetail.getStudentClass());
            studentInfo.setStudentId(accountDetail.getStudentId());
            studentInfo.setAccountId(accountDetail.getAccountId());
            studentInfo.setClassId(classId);

            result.add(studentInfo);
        }
        return result;
    }
    public ResponseEntity<?>getAccountDetail(int accountId){
        Optional<Account> account=accountRepository.findById(accountId);
        if(account.isPresent()&&account.get().getType()== Role.GV){
            TeacherAccountDetail accountDetail=new TeacherAccountDetail(account.get().getUserId(),account.get().getFullName(),account.get().getEmail(),account.get().getPhoneNumber());
            return new ResponseEntity<TeacherAccountDetail>(accountDetail,HttpStatus.OK);
        }
        if(account.isPresent()&&account.get().getType()== Role.SV){
            StudentAccountDetail accountDetail=getStudentAccountDetail(accountId);
            return new ResponseEntity<StudentAccountDetail>(accountDetail,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
