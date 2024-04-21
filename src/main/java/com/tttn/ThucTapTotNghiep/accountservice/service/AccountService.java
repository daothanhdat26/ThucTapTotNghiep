package com.tttn.ThucTapTotNghiep.accountservice.service;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.accountservice.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Transactional
@Service
public class AccountService {
    AccountRepository accountRepository;


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
        ac.setUser_password(account.getUser_password());
        ac.setUser_email(account.getUser_email());
        ac.setUser_type(account.getUser_type());
        ac.setPhone_number(account.getPhone_number());
        ac.setFull_name(account.getFull_name());
        accountRepository.save(ac);
        return ac;
    }

}
