package com.tttn.ThucTapTotNghiep.accountservice.service;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.accountservice.repository.AccountRepository;
import com.tttn.ThucTapTotNghiep.groupService.model.Student;
import com.tttn.ThucTapTotNghiep.groupService.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class AccountService {
    AccountRepository accountRepository;
    StudentRepository studentRepository;

    public AccountService(AccountRepository accountRepository, StudentRepository studentRepository) {
        this.accountRepository = accountRepository;
        this.studentRepository = studentRepository;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }



    public Account save(Account ac) {
        return accountRepository.save(ac);
    }


    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }


    public Account updateAc(Integer id, Account account){
        Account ac = accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Khong co account voi id:"+id));
        ac.setPassword(account.getPassword());
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


                if (mssvCell != null && hoCell != null && tenCell !=null) {
                    if (mssvCell.getCellType() == CellType.BLANK && hoCell.getCellType() == CellType.BLANK && tenCell.getCellType() == CellType.BLANK) {
                        continue;
                    }
                    String user_email =  mssvCell.getStringCellValue()+"@student.edu.vn";
                    String user_password = mssvCell.getStringCellValue();
                    String user_type = "SinhVien";
                    String user_fullname= hoCell.getStringCellValue()+" "+tenCell.getStringCellValue();

                    // kiem tra email ton tai chua?
                    // neu chua ton tai thi moi luu
                    if (accountRepository.findByEmail(user_email) == null) {
                        Account newAccount = new Account(user_password, user_email, user_type, user_fullname);
                        Account savedAccount = accountRepository.save(newAccount);
                        // Lưu userId vào bảng student_list
                        studentRepository.save(new Student(idClass, savedAccount.getUserId()));
                    } else {
                        int userId = accountRepository.findUserIdByEmail(user_email);
                        studentRepository.save(new Student(idClass, userId));
                    }

                }
            }
            // Đóng luồng
            workbook.close();
            inputStream.close();

            return "Dữ liệu đã được import thành công";
        } catch (Exception e) {
            e.printStackTrace();
            return "Đã xảy ra lỗi trong quá trình import dữ liệu từ file Excel";
        }
    }

}
