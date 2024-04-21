package com.tttn.ThucTapTotNghiep.accountservice.controller;


import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Iterator;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public String importAccoutFromExcel(@RequestParam("file") MultipartFile multipartFile) {
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
                    // Lưu vào database
                    //Code luu

                        // email da ton tai
                        accountService.save(new Account(user_password,user_email,user_type,user_fullname));

                    
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
