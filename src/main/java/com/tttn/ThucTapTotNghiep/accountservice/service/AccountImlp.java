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
public class AccountImlp implements AccountService {
    private AccountRepository accountRepository;

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Optional<Account> findById(Integer integer) {
        return accountRepository.findById(integer);
    }

    @Override
    public Account save(Account ac) {
        return accountRepository.save(ac);
    }

    @Override
    public void deleteById(Integer integer) {
        accountRepository.deleteById(integer);
    }

    @Override
    public Account updateAc(Integer id, Account ac){
        Account ac1=accountRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Khong co account voi id:"+id));

        return ac1;
    }
}
