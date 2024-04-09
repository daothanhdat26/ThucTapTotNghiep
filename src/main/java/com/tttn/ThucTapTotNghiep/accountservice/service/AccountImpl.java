package com.tttn.ThucTapTotNghiep.accountservice.service;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import com.tttn.ThucTapTotNghiep.accountservice.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccountImpl implements AccountService {
    private  AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

//    @Override
//    public Optional<Account> findById(Integer integer) {
//        return accountRepository.findById(integer);
//    }

    @Override
    public Account save(Account ac) {
        return accountRepository.save(ac);
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
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
