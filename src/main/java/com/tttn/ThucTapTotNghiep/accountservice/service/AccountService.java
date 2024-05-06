package com.tttn.ThucTapTotNghiep.accountservice.service;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.accountservice.model.Role;
import com.tttn.ThucTapTotNghiep.accountservice.model.StudentDetail;
import com.tttn.ThucTapTotNghiep.accountservice.repository.AccountRepository;
import com.tttn.ThucTapTotNghiep.accountservice.repository.StudentDetailRepository;
import com.tttn.ThucTapTotNghiep.groupService.model.Student;
import com.tttn.ThucTapTotNghiep.groupService.repository.StudentRepository;
import com.tttn.ThucTapTotNghiep.groupService.service.GroupService;
import com.tttn.ThucTapTotNghiep.subjectclassservice.model.SubjectClass;
import com.tttn.ThucTapTotNghiep.subjectclassservice.repository.SubjectClassReponsitory;
import com.tttn.ThucTapTotNghiep.subjectclassservice.service.SubjectClassService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
@Service
public class AccountService {
    AccountRepository accountRepository;
    StudentRepository studentRepository;
    StudentDetailRepository studentDetailRepository;
    SubjectClassReponsitory subjectClassReponsitory;
    PasswordEncoder passwordEncoder;
    GroupService groupService;

    public AccountService(AccountRepository accountRepository, StudentRepository studentRepository, StudentDetailRepository studentDetailRepository, SubjectClassReponsitory subjectClassReponsitory, PasswordEncoder passwordEncoder, GroupService groupService) {
        this.accountRepository = accountRepository;
        this.studentRepository = studentRepository;
        this.studentDetailRepository = studentDetailRepository;
        this.subjectClassReponsitory = subjectClassReponsitory;
        this.passwordEncoder = passwordEncoder;
        this.groupService = groupService;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }



    public Account save(Account ac) {
        String password=passwordEncoder.encode(ac.getPassword());
        ac.setType(Role.GV);
        ac.setPassword(password);
        return accountRepository.save(ac);
    }


    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }


    public Account updateAc(Integer id, Account account){
        Account ac = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Khong co account voi id:"+id));
        ac.setPassword(passwordEncoder.encode(account.getPassword()));
        ac.setEmail(account.getEmail());
        ac.setType(account.getType());
        ac.setPhoneNumber(account.getPhoneNumber());
        ac.setFullName(account.getFullName());
        accountRepository.save(ac);
        return ac;
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }


    public String importAccoutFromExcel( Integer idClass,@RequestParam("file") MultipartFile multipartFile) {
        try {
            // Đọc file Excel
            InputStream inputStream = multipartFile.getInputStream();
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0); // Đọc sheet đầu tiên

            // Xác định chỉ số cột MSSV và Họ Tên
            int mssvColumnIndex = 2;
            int hoColumnIndex = 3;
            int tenColumnIndex =4;
            int lopColumIndex=5;

            // Duyệt qua từng dòng trong file Excel
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (row.getRowNum() < 6) { // Bỏ qua các dòng trước dòng 7
                    continue;
                }

                Cell mssvCell = row.getCell(mssvColumnIndex);
                Cell hoCell = row.getCell(hoColumnIndex);
                Cell tenCell = row.getCell(tenColumnIndex);
                Cell lopCell = row.getCell(lopColumIndex);


                if (mssvCell != null && hoCell != null && tenCell !=null&&lopCell!=null) {
                    if (mssvCell.getCellType() == CellType.BLANK && hoCell.getCellType() == CellType.BLANK && tenCell.getCellType() == CellType.BLANK&&lopCell.getCellType()==CellType.BLANK) {
                        continue;
                    }
                    String user_email =  mssvCell.getStringCellValue()+"@student.edu.vn";
                    String user_password = mssvCell.getStringCellValue();
                    String user_type = "SinhVien";
                    String user_fullname= hoCell.getStringCellValue()+" "+tenCell.getStringCellValue();
                    String user_lop=lopCell.getStringCellValue();
                    String user_studentId=mssvCell.getStringCellValue();
                    // kiem tra email ton tai chua?
                    // neu chua ton tai thi moi luu
                    if (accountRepository.findByEmail(user_email) == null) {
                        Account newAccount = new Account(passwordEncoder.encode(user_password), user_email, Role.SV, user_fullname);
                        Account savedAccount = accountRepository.save(newAccount);
                        // Lưu userId vào bảng student_list
                        studentRepository.save(new Student(idClass, savedAccount.getUserId()));
                        StudentDetail studentDetail = new StudentDetail(savedAccount.getUserId(),user_studentId,user_lop);
                        studentDetailRepository.save(studentDetail);
                    } else {
                        //them sinh vien vao lop
                        int userId = accountRepository.findUserIdByEmail(user_email);
                        studentRepository.save(new Student(idClass, userId));
                    }

                }
            }
            // Đóng luồng
            workbook.close();
            inputStream.close();

            //Luu group theo random
            Optional<SubjectClass> subjectClass=subjectClassReponsitory.findById(idClass);
            if(subjectClass.isPresent()&& Objects.equals(subjectClass.get().getGroupRegisterMethod(), "RANDOM")){
                groupService.assignStudentsToRandomGroups(idClass,subjectClass.get().getNumberOfGroup(),subjectClass.get().getMemberPerGroup());
            }
            return "Dữ liệu đã được import thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi trong quá trình import dữ liệu từ file Excel";
        }
    }

}
